package com.picklerisk.grabber;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picklerisk.grabber.JsonSchema.Iex.KeyStats;
import com.picklerisk.grabber.JsonSchema.Iex.KeyStatsEntry;
import com.picklerisk.grabber.JsonSchema.Iex.News;
import com.picklerisk.grabber.JsonSchema.Iex.NewsEntry;
import com.picklerisk.grabber.JsonSchema.Iex.TimeSeriesDailyAdjusted;
import com.picklerisk.grabber.JsonSchema.Iex.TimeSeriesDailyAdjustedEntry;
import com.picklerisk.grabber.persistence.IexKeyStatsMongoRepository;
import com.picklerisk.grabber.persistence.IexNewsMongoRepository;
import com.picklerisk.grabber.persistence.IexTimeSeriesMongoRepository;
import com.picklerisk.grabber.persistence.PersistenceManager;

@Service
public class IexGrabber {

	// TODO: [Priority-1] Pull repetitive code out into interface/abstract base class

	private static final Logger log = LoggerFactory.getLogger(IexGrabber.class);
	
	private IexTimeSeriesMongoRepository timeSeriesStore;
	private IexKeyStatsMongoRepository keyStatsStore;
	private IexNewsMongoRepository newsStore;
	
	private PersistenceManager manager;

	static final String locationSandPDefinitionFile = "src/main/resources/sandpMakeup.txt";

	public IexGrabber() {

	}
	
	public void setStorage(IexTimeSeriesMongoRepository timeSeriesStore,
						   IexKeyStatsMongoRepository keyStatsStore,
						   IexNewsMongoRepository newsStore) {
		
		this.timeSeriesStore = timeSeriesStore;
		this.keyStatsStore = keyStatsStore;
		this.newsStore = newsStore;
	}
	
	public void setManager(PersistenceManager manager) {
		this.manager = manager;
	}

	public TimeSeriesDailyAdjusted grabAdjustedDailyHistory(String sym) {
		final String url = "https://api.iextrading.com/1.0/stock/" 
				+ sym
				+ "/chart/5y";

		TimeSeriesDailyAdjusted tickData = new TimeSeriesDailyAdjusted();

		try {
			log.info("Requesting: " + url);

			RestTemplate restTemplate = new RestTemplate();
			TimeSeriesDailyAdjustedEntry[] allEntries = restTemplate.getForObject(url, TimeSeriesDailyAdjustedEntry[].class);		
			tickData.setEntries(Arrays.asList(allEntries));
			tickData.setSymbol(sym);

			// TODO: Proper API checks to ensure following the API Key rules
			if (!Optional.ofNullable(allEntries).isPresent())
				log.warn(sym + " not received. IEX API returned malformed JSON");

		} catch (org.springframework.web.client.HttpClientErrorException e) {
			log.warn(sym + " not found in IEX.");
		}

		return tickData;
	}
	
	public KeyStats grabKeyStatsForSymbol(String sym) {
		final String url = "https://api.iextrading.com/1.0/stock/" 
				+ sym
				+ "/stats";

		KeyStats keyStats = new KeyStats();
		
		try {
			log.info("Requesting : " + url);

			RestTemplate restTemplate = new RestTemplate();
			KeyStatsEntry entry = restTemplate.getForObject(url, KeyStatsEntry.class);		
			keyStats.setEntry(entry);
			keyStats.setSymbol(sym);
			keyStats.setDateLastRefreshed(LocalDate.now().toString());

			// TODO: Proper API checks to ensure following the API Key rules
			if (!Optional.ofNullable(entry.getCompanyName()).isPresent())
				log.warn(sym + " not received. IEX API returned malformed JSON");

		} catch (org.springframework.web.client.HttpClientErrorException e) {
			log.warn(sym + " not found in IEX.");
		}

		return keyStats;
	}
	
	public News grabNewsForSymbol(String sym) {
		final String url = "https://api.iextrading.com/1.0/stock/" 
				+ sym
				+ "/news/last/10";

		News news = new News();
		
		try {
			log.info("Requesting : " + url);

			RestTemplate restTemplate = new RestTemplate();
			NewsEntry[] entry = restTemplate.getForObject(url, NewsEntry[].class);		
			news.setEntries(Arrays.asList(entry));
			news.setSymbol(sym);
			news.setDateLastRefreshed(LocalDate.now().toString());

			// TODO: Proper API checks to ensure following the API Key rules
			if (!Optional.ofNullable(entry).isPresent())
				log.warn(sym + " not received. IEX API returned malformed JSON");

		} catch (org.springframework.web.client.HttpClientErrorException e) {
			log.warn(sym + " not found in IEX.");
		}

		return news;
	}
	
	public void grabAllNews() {
		List<String> symbols = manager.getTrackedSymbols();
		for (String sym : symbols) {
			log.info("Getting news for: " + sym);
			News news = grabNewsForSymbol(sym);
			log.info("Saving news for: " + sym);
			newsStore.save(news);
		}
	}
	
	public void grabAllKeyStats() {
		List<String> symbols = manager.getTrackedSymbols();
		for (String sym : symbols) {
			log.info("Getting key stats for: " + sym);
			KeyStats stats = grabKeyStatsForSymbol(sym);
			log.info("Saving key stats for: " + sym);
			keyStatsStore.save(stats);
		}
	}

	public void grabSandPAdjustedDailyHistory() throws FileNotFoundException {

		timeSeriesStore.deleteAll();
		Scanner s = new Scanner(new File(locationSandPDefinitionFile));
		while (s.hasNextLine()) {
			String sym = s.nextLine();
			log.info("Grabbing for '" + sym + "'");
			TimeSeriesDailyAdjusted tickData = grabAdjustedDailyHistory(sym);
			if (tickData.getEntries().size() > 0) {
				log.info("Adding '" + tickData.getSymbol() + "' to Mongo Repository");
				timeSeriesStore.addData(tickData);
				manager.addTrackedSymbol(tickData.getSymbol());
			}
		}
		manager.saveNewRefreshedDate();

		s.close();
	}
}
