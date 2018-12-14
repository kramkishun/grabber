package com.picklerisk.grabber.JsonSchema.Iex;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MetaDataStore {
	@Id
	private String id;
	private String dailiesLastUpdatedDate;
	private List<String> trackedDailySymbols;
	
	public MetaDataStore() {
		trackedDailySymbols = new ArrayList<>();
	}
	
	public String getDailiesLastUpdatedDate() {
		return dailiesLastUpdatedDate;
	}

	public void setDailiesLastUpdatedDate(String dailiesLastUpdatedDate) {
		this.dailiesLastUpdatedDate = dailiesLastUpdatedDate;
	} 

	public List<String> getTrackedDailySymbols() {
		if (!Optional.ofNullable(trackedDailySymbols).isPresent()) {
			trackedDailySymbols = new ArrayList<>();
		}
		return trackedDailySymbols;
	}

	public void setTrackedDailySymbols(List<String> trackedDailySymbols) {
		this.trackedDailySymbols = trackedDailySymbols;
	}	
	
	public void addTrackedDailySymbol(String sym) {
		trackedDailySymbols.add(sym);
	}
}
