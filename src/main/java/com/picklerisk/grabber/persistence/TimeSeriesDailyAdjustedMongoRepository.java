package com.picklerisk.grabber.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.picklerisk.grabber.JsonSchema.AlphaVantage.TimeSeriesDailyAdjusted;

public interface TimeSeriesDailyAdjustedMongoRepository 
	extends MongoRepository<TimeSeriesDailyAdjusted, String> {
	
}
