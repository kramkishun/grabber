package com.picklerisk.grabber.JsonSchema.AlphaVantage;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Sample: https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=MSFT&outputsize=full&apikey=demo
 * NOTE: DO NOT USE AS IS. This schema must be translated to IEX schema in order to be stored into MongoDB.
 * @author kr
 * @deprecated  AlphaVantage was used in the beginning when developing but has been since replaced by IEX. If
 * 				you wish to use AlphaVantage as a source, the resulting object must be translated to IEX
 * 				schema prior to inserting into Mongo.
 */
@Document
@Deprecated
public class TimeSeriesDailyAdjusted {
	@JsonProperty ("Meta Data")
	TimeSeriesDailyAdjustedMetaData metaData;

	@JsonProperty ("Time Series (Daily)")
	TimeSeriesDailyAdjustedDay allDailyEntries;

	public TimeSeriesDailyAdjustedMetaData getMetaData() {
		return metaData;
	}

	public void setMetaData(TimeSeriesDailyAdjustedMetaData metaData) {
		this.metaData = metaData;
	}

	public TimeSeriesDailyAdjustedDay getEntries() {
		return allDailyEntries;
	}

	public void setEntries(TimeSeriesDailyAdjustedDay entries) {
		this.allDailyEntries = entries;
	}
}



