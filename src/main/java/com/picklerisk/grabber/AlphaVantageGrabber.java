package com.picklerisk.grabber;

import com.picklerisk.grabber.JsonSchema.AlphaVantage.TimeSeriesDailyAdjusted;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AlphaVantageGrabber {
	
	private static final Logger log = LoggerFactory.getLogger(AlphaVantageGrabber.class);
	
	final String avURL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=MSFT&outputsize=full&apikey=demo";
	final String API_KEY = "OPBP3MAS5EJY0X33";
	final String locationSandPDefinitionFile = "src/main/resources/sandpMakeup.txt";
	
	public AlphaVantageGrabber() {

	}
	
	public TimeSeriesDailyAdjusted grab() {
        RestTemplate restTemplate = new RestTemplate();
        
		TimeSeriesDailyAdjusted tickData = restTemplate.getForObject(avURL, TimeSeriesDailyAdjusted.class);		
        return tickData;
	}
	
	public TimeSeriesDailyAdjusted grabAdjustedDailyHistory(String sym) {
		final String url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=" 
				+ sym 
				+ "&outputsize=full&apikey=" 
				+ API_KEY;
        RestTemplate restTemplate = new RestTemplate();
        
		TimeSeriesDailyAdjusted tickData = restTemplate.getForObject(url, TimeSeriesDailyAdjusted.class);		
		
		return tickData;
	}
	
	public List<TimeSeriesDailyAdjusted> grabSandPAdjustedDailyHistory() throws FileNotFoundException {
		List<TimeSeriesDailyAdjusted> allTicks = new ArrayList<>();
		
		Scanner s = new Scanner(new File(locationSandPDefinitionFile));
		while (s.hasNextLine()) {
			String sym = s.nextLine();
			allTicks.add(grabAdjustedDailyHistory(sym));
			log.info("Grabbing for " + sym);
		}	
		
		s.close();
		
		return allTicks;
	}
}