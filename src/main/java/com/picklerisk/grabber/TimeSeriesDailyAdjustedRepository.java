package com.picklerisk.grabber;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.picklerisk.grabber.JsonSchema.AlphaVantage.TimeSeriesDailyAdjusted;

public interface TimeSeriesDailyAdjustedRepository 
	extends MongoRepository<TimeSeriesDailyAdjusted, String> {
	
}
