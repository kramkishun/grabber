package com.picklerisk.grabber.JsonSchema.Iex;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class KeyStats {
	@Id
	private String id;
	private String symbol;
	private String dateLastRefreshed;
	private KeyStatsEntry entry;
	
	public String getId() {
		return id;
	}
	public String getSymbol() {
		return symbol;
	}
	public String getDateLastRefreshed() {
		return dateLastRefreshed;
	}
	public KeyStatsEntry getEntry() {
		return entry;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public void setDateLastRefreshed(String dateLastRefreshed) {
		this.dateLastRefreshed = dateLastRefreshed;
	}
	public void setEntry(KeyStatsEntry entry) {
		this.entry = entry;
	}
}
