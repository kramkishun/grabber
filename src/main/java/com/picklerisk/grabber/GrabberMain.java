package com.picklerisk.grabber;


import java.io.FileNotFoundException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.picklerisk.grabber.JsonSchema.Iex.TimeSeriesDailyAdjusted;
import com.picklerisk.grabber.persistence.IexTimeSeriesMongoRepository;

@SpringBootApplication
public class GrabberMain implements CommandLineRunner {
	
    private static final Logger log = LoggerFactory.getLogger(GrabberMain.class);
    
    @Autowired
    private IexTimeSeriesMongoRepository mongoAdapter;
    
    @Autowired
    private IexGrabber grabber;
    
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
		mongoAdapter.deleteAll();
		List<TimeSeriesDailyAdjusted> allSandP = grabber.grabSandPAdjustedDailyHistory();
		for (TimeSeriesDailyAdjusted sym : allSandP) {
			log.info("Adding " + sym.toString() + " to MongoDB");
			mongoAdapter.save(sym);
		}
	}
}

