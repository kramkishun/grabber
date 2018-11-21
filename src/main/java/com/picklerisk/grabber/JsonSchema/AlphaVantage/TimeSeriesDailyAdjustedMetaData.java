package com.picklerisk.grabber.JsonSchema.AlphaVantage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TimeSeriesDailyAdjustedMetaData {

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