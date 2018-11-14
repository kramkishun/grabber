package com.picklerisk.grabber;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picklerisk.grabber.JsonSchema.Iex.TimeSeriesDailyAdjusted;
import com.picklerisk.grabber.JsonSchema.Iex.TimeSeriesDailyAdjustedEntry;

@Service
public class IexGrabber {
	
	// TODO: Pull repetitive code out into interface/abstract base class

	private static final Logger log = LoggerFactory.getLogger(IexGrabber.class);
	
	static final String locationSandPDefinitionFile = "src/main/resources/sandpMakeup.txt";
	
	public IexGrabber() {

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
	
	public List<TimeSeriesDailyAdjusted> grabSandPAdjustedDailyHistory() throws FileNotFoundException {
		
		// there's no actual reason to store this in a list - doing it just to
		// be able to inspect/debug
		// TODO: remove list
		List<TimeSeriesDailyAdjusted> allTicks = new ArrayList<>();
		
		Scanner s = new Scanner(new File(locationSandPDefinitionFile));
		while (s.hasNextLine()) {
			String sym = s.nextLine();
			log.info("Grabbing for '" + sym + "'");
			TimeSeriesDailyAdjusted tickData = grabAdjustedDailyHistory(sym);
			if (tickData.getEntries().size() > 0)
				allTicks.add(tickData);
		}	
		
		s.close();
		
		return allTicks;
	}
}
