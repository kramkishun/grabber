package com.picklerisk.grabber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.picklerisk.grabber.JsonSchema.AlphaVantage.TimeSeriesDailyAdjusted;

@Service
public class MongoDbAdapter {
	
	@Autowired
	private TimeSeriesDailyAdjustedRepository repository;
	
	public void addTimeSeriesData(TimeSeriesDailyAdjusted data) {
		repository.deleteAll();
		repository.save(data);
	}	
}