package com.picklerisk.grabber.JsonSchema.AlphaVantage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TimeSeriesDailyAdjustedEntry {
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