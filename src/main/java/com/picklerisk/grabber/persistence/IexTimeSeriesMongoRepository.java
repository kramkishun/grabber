package com.picklerisk.grabber.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.picklerisk.grabber.JsonSchema.Iex.TimeSeriesDailyAdjusted;

public interface IexTimeSeriesMongoRepository 
	extends MongoRepository<TimeSeriesDailyAdjusted, String> {
	
}

