package com.picklerisk.grabber;

import com.picklerisk.grabber.JsonSchema.AlphaVantage.TimeSeriesDailyAdjusted;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AlphaVantageGrabber {
	final String avURL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=MSFT&outputsize=full&apikey=demo";
	
	public AlphaVantageGrabber() {

	}
	
	TimeSeriesDailyAdjusted grab() {
        RestTemplate restTemplate = new RestTemplate();
        
		TimeSeriesDailyAdjusted tickData = restTemplate.getForObject(avURL, TimeSeriesDailyAdjusted.class);		
        return tickData;
	}
}