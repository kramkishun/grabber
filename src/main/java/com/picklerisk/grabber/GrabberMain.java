package com.picklerisk.grabber;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.picklerisk.grabber.JsonSchema.Iex.TimeSeriesDailyAdjusted;
import com.picklerisk.grabber.JsonSchema.Iex.TimeSeriesDailyAdjustedEntry;
import com.picklerisk.grabber.persistence.CsvFileRepository;
import com.picklerisk.grabber.persistence.IexKeyStatsMongoRepository;
import com.picklerisk.grabber.persistence.IexNewsMongoRepository;
import com.picklerisk.grabber.persistence.IexTimeSeriesMongoRepository;
import com.picklerisk.grabber.persistence.PersistenceManager;

@SpringBootApplication
public class GrabberMain implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(GrabberMain.class);

	@Autowired
	private IexTimeSeriesMongoRepository timeSeriesStorage;
	//private CsvFileRepository<TimeSeriesDailyAdjustedEntry> storageAdapter;

	@Autowired
	private IexKeyStatsMongoRepository keyStatsStorage;
	
	@Autowired
	private IexNewsMongoRepository newsStorage;
	
	@Autowired
	private IexGrabber grabber;
	
	@Autowired
	private PersistenceManager persistenceManager;

	public static void main(String[] args) {
		log.info("Start Main");
		SpringApplication.run(GrabberMain.class, args);
		log.info("End Main");
	}

	public GrabberMain() {

	}
	
	private void init() {
		grabber.setStorage(timeSeriesStorage, keyStatsStorage, newsStorage);
		grabber.setManager(persistenceManager);
	}

	@Bean
	public File getDefaultFile() {
		return new File("not_defined.txt");
	}

	@Override
	public void run(String... args) throws Exception {
		init();
		
		// TODO: [Priority-2] Update to run as Daemon and update Mongo objects w/ up to date data.
		log.info("Start of 'run'");
//		resetAllDailyAdjusted();
		grabber.grabAllNews();
		log.info("End of 'run'");
	}
	
	/**
	 * Checks to see if there is new daily information available and refreshes the existing 
	 * data if so. Checks all symbols that are already in the database.
	 */
	public void refreshAllDailyAdjusted() {
		if (!persistenceManager.refreshNeeded())
			return;
		
		// TODO: Delta update
	}

	/**
	 * Rebuilds the entire Daily Adjust Database for the last 5 years from scratch.
	 * If there are entries already in the database, they will be deleted.
	 * @throws FileNotFoundException
	 */
	public void resetAllDailyAdjusted() throws FileNotFoundException {
		grabber.grabSandPAdjustedDailyHistory();
		
		keyStatsStorage.deleteAll();
		grabber.grabAllKeyStats();
		
		newsStorage.deleteAll();
		grabber.grabAllNews();
	}
}

