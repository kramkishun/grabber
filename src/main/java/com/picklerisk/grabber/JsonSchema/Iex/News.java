package com.picklerisk.grabber.JsonSchema.Iex;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document
public class News {
	@Id
	private String Id;
	private String symbol;
	private String dateLastRefreshed;
	private List<NewsEntry> entries;
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getDateLastRefreshed() {
		return dateLastRefreshed;
	}
	public void setDateLastRefreshed(String dateLastRefreshed) {
		this.dateLastRefreshed = dateLastRefreshed;
	}
	public List<NewsEntry> getEntries() {
		return entries;
	}
	public void setEntries(List<NewsEntry> entries) {
		this.entries = entries;
	}
}
