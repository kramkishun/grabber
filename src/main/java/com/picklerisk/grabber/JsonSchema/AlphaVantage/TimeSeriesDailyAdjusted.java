package com.picklerisk.grabber.JsonSchema.AlphaVantage;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Sample: https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=MSFT&outputsize=full&apikey=demo
 * 
 * @author kr
 */

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

class TimeSeriesDailyAdjustedDay {
	Map<String, TimeSeriesDailyAdjustedEntry> entries = new HashMap<>();
	
	@JsonAnySetter
	void addEntry(String date, TimeSeriesDailyAdjustedEntry entry) {
		entries.put(date, entry);
	}
}

class TimeSeriesDailyAdjustedEntry {
	@JsonProperty ("1. open")
	float open;

	@JsonProperty ("2. high")
	float high;

	@JsonProperty ("3. low")
	float low;

	@JsonProperty ("4. close")
	float close;

	@JsonProperty ("5. adjusted close")
	float adjustedClose;

	@JsonProperty ("6. volume")
	float volume;

	@JsonProperty ("7. dividend amount")
	float dividendAmount;
	
	@JsonProperty ("8. split coefficient")
	float splitCoefficient;
	
	public float getOpen() {
		return open;
	}

	public void setOpen(float open) {
		this.open = open;
	}

	public float getHigh() {
		return high;
	}

	public void setHigh(float high) {
		this.high = high;
	}

	public float getLow() {
		return low;
	}

	public void setLow(float low) {
		this.low = low;
	}

	public float getClose() {
		return close;
	}

	public void setClose(float close) {
		this.close = close;
	}

	public float getAdjustedClose() {
		return adjustedClose;
	}

	public void setAdjustedClose(float adjustedClose) {
		this.adjustedClose = adjustedClose;
	}

	public float getVolume() {
		return volume;
	}

	public void setVolume(float volume) {
		this.volume = volume;
	}

	public float getDividendAmount() {
		return dividendAmount;
	}

	public void setDividendAmount(float dividendAmount) {
		this.dividendAmount = dividendAmount;
	}

	public float getSplitCoefficient() {
		return splitCoefficient;
	}

	public void setSplitCoefficient(float splitCoefficient) {
		this.splitCoefficient = splitCoefficient;
	}
}

class TimeSeriesDailyAdjustedMetaData {
	
	@JsonProperty ("1. Information")
	String information;
	
	@JsonProperty ("2. Symbol")
	String symbol;
	
	@JsonProperty ("3. Last Refreshed")
	String lastRefreshed;
	
	@JsonProperty ("4. Output Size")
	String outputSize;
	
	@JsonProperty ("5. Time Zone")
	String timeZone;
	
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getLastRefreshed() {
		return lastRefreshed;
	}
	public void setLastRefreshed(String lastRefreshed) {
		this.lastRefreshed = lastRefreshed;
	}
	public String getOutputSize() {
		return outputSize;
	}
	public void setOutputSize(String outputSize) {
		this.outputSize = outputSize;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
}