package com.picklerisk.grabber.JsonSchema.Iex;

public class TimeSeriesDailyAdjustedEntry {
	private String date;
	private float open;
	private float high;
	private float low;
	private float close;
	private float volume;
	private float unadjustedVolume;
	private float change;
	private float changePercent;
	private String vwap;
	private String label;
	private String changeOverTime;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
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
	public float getVolume() {
		return volume;
	}
	public void setVolume(float volume) {
		this.volume = volume;
	}
	public float getUnadjustedVolume() {
		return unadjustedVolume;
	}
	public void setUnadjustedVolume(float unadjustedVolume) {
		this.unadjustedVolume = unadjustedVolume;
	}
	public float getChange() {
		return change;
	}
	public void setChange(float change) {
		this.change = change;
	}
	public float getChangePercent() {
		return changePercent;
	}
	public void setChangePercent(float changePercent) {
		this.changePercent = changePercent;
	}
	public String getVwap() {
		return vwap;
	}
	public void setVwap(String vwap) {
		this.vwap = vwap;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getChangeOverTime() {
		return changeOverTime;
	}
	public void setChangeOverTime(String changeOverTime) {
		this.changeOverTime = changeOverTime;
	}
}
