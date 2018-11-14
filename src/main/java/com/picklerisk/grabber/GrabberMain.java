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
import com.picklerisk.grabber.persistence.IexTimeSeriesMongoRepository;

@SpringBootApplication
public class GrabberMain implements CommandLineRunner {
	
    private static final Logger log = LoggerFactory.getLogger(GrabberMain.class);
    
    @Autowired
    //private IexTimeSeriesMongoRepository storageAdapter;
    private CsvFileRepository<TimeSeriesDailyAdjustedEntry> storageAdapter;
    
    @Autowired
    private IexGrabber grabber;
    
	public static void main(String[] args) {
		log.info("Start Main");
		SpringApplication.run(GrabberMain.class, args);
		log.info("End Main");
	}

	public GrabberMain( ) {
		
	}
	
	@Bean
	public File getDefaultFile() {
		return new File("not_defined.txt");
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO: [Priority-2] Update to run as Daemon and update Mongo objects w/ up to date data.
		log.info("Start of 'run'");
		refreshAllAdjustedDailies();
		log.info("End of 'run'");
	}
	
	public void refreshAllAdjustedDailies() throws FileNotFoundException {
		
		List<TimeSeriesDailyAdjusted> allSandP = grabber.grabSandPAdjustedDailyHistory();
		for (TimeSeriesDailyAdjusted sym : allSandP) {
			log.info("Adding " + sym.getSymbol() + " to Persistent Storage");
			storageAdapter.setFile(new File("data/" + sym.getSymbol() + ".csv"));	
			storageAdapter.addData(sym.getEntries());
		}
	}
}

