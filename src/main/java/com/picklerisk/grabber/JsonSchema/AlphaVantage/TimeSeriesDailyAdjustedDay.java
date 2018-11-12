package com.picklerisk.grabber.JsonSchema.AlphaVantage;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;

public class TimeSeriesDailyAdjustedDay {
	Map<String, TimeSeriesDailyAdjustedEntry> entries = new HashMap<>();
	
	@JsonAnySetter
	void addEntry(String date, TimeSeriesDailyAdjustedEntry entry) {
		entries.put(date, entry);
	}
}