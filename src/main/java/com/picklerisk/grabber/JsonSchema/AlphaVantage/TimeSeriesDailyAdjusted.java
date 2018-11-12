package com.picklerisk.grabber.JsonSchema.AlphaVantage;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Sample: https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=MSFT&outputsize=full&apikey=demo
 * 
 * @author kr
 */
@Document
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



