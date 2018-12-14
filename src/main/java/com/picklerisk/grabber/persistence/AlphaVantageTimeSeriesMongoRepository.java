package com.picklerisk.grabber.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.picklerisk.grabber.JsonSchema.AlphaVantage.TimeSeriesDailyAdjusted;

public interface AlphaVantageTimeSeriesMongoRepository 
	extends MongoRepository<TimeSeriesDailyAdjusted, String>, PersistentStore<TimeSeriesDailyAdjusted> {

	@Override
	default void clearData() {
		deleteAll();
	}

	@Override
	default void addData(TimeSeriesDailyAdjusted data) {
		// NOTE: Don't do this - as the application is now built mostly on Iex format
		// we need to translate this schema into Iex format prior to entering into Mongo
		save(data);
	}
}
