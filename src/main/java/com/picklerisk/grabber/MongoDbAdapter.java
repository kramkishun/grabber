package com.picklerisk.grabber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picklerisk.grabber.JsonSchema.AlphaVantage.TimeSeriesDailyAdjusted;

@Service
public class MongoDbAdapter {
	
	@Autowired
	private TimeSeriesDailyAdjustedRepository repository;
	
	public void clearTimeSeriesData() {
		repository.deleteAll();
	}
	
	public void addTimeSeriesData(TimeSeriesDailyAdjusted data) {
		repository.save(data);
	}	
}

