package com.picklerisk.grabber.JsonSchema.Iex;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Chosen as the standard schema for MongoDB. Any data from other sources
 * will have to be converted to Iex format to be stored into MongoDB.
 */

@Document
public class TimeSeriesDailyAdjusted {
	@Id
	private String id;
	
	private List<TimeSeriesDailyAdjustedEntry> entries;
	private String symbol;

	public TimeSeriesDailyAdjusted() {
		entries = new ArrayList<>();
	}

	public List<TimeSeriesDailyAdjustedEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<TimeSeriesDailyAdjustedEntry> entries) {
		this.entries = entries;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}


