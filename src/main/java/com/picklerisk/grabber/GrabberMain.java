package com.picklerisk.grabber;


import java.io.FileNotFoundException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.picklerisk.grabber.JsonSchema.AlphaVantage.TimeSeriesDailyAdjusted;
import com.picklerisk.grabber.persistence.PersistentStore;
import com.picklerisk.grabber.persistence.TimeSeriesDailyAdjustedMongoRepository;

@SpringBootApplication
public class GrabberMain implements CommandLineRunner {
	
    private static final Logger log = LoggerFactory.getLogger(GrabberMain.class);
    
    @Autowired
    private PersistentStore<TimeSeriesDailyAdjustedMongoRepository,
    	TimeSeriesDailyAdjusted> mongoAdapter;
    
    @Autowired
    private AlphaVantageGrabber grabber;
    
	public static void main(String[] args) {
		log.info("Start Main");
		SpringApplication.run(GrabberMain.class, args);
		log.info("End Main");
	}

	public GrabberMain( ) {
		
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Start of 'run'");
		refreshAllAdjustedDailies();
		log.info("End of 'run'");
	}
	
	public void refreshAllAdjustedDailies() throws FileNotFoundException {
		mongoAdapter.clearData();
		List<TimeSeriesDailyAdjusted> allSandP = grabber.grabSandPAdjustedDailyHistory();
		for (TimeSeriesDailyAdjusted sym : allSandP) {
			log.info("Adding " + sym.toString() + " to MongoDB");
			mongoAdapter.addData(sym);
		}
	}
}

