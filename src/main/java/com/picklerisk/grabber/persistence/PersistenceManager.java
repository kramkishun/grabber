package com.picklerisk.grabber.persistence;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.picklerisk.grabber.JsonSchema.Iex.MetaDataStore;

@Component
public class PersistenceManager {
	
	private MetaDataStore mds;
	
	@Autowired
	private MetaDataMongoRepository mongo;
	
	public PersistenceManager(MetaDataMongoRepository mongo) {
		this.mongo = mongo;
		this.mds = getCurrentMetaDataStore();
	}
	
	public LocalDate getLastRefreshedDate() {
		return LocalDate.parse(mds.getDailiesLastUpdatedDate(), 
							   DateTimeFormatter.ISO_DATE);

	}
	
	public void saveNewRefreshedDate() {
		mds.setDailiesLastUpdatedDate(LocalDate.now().toString());
		mongo.save(mds);
	}
	
	public void addTrackedSymbol(String sym) {
		mds.addTrackedDailySymbol(sym);
		mongo.save(mds);
	}
	
	public List<String> getTrackedSymbols() {
		return mds.getTrackedDailySymbols();
	}
	
	/**
	 * @return 		true	if refresh is needed based on a simple date comparison;
	 * 				false	otherwise
	 */
	public boolean refreshNeeded() {
		// Not smart enough to know if trading actually occurred on the days
		return getLastRefreshedDate().isBefore(LocalDate.now());
	}
	
	private MetaDataStore getCurrentMetaDataStore() {
		MetaDataStore mds;
		List<MetaDataStore> mdsList = mongo.findAll();
		
		if (mdsList.size() != 1) {
			mds = new MetaDataStore();
			mds.setDailiesLastUpdatedDate("0000-00-00");
			mongo.deleteAll();
			mongo.save(mds);
			mds = mongo.findAll().get(0);
		} else {
			mds = mdsList.get(0);
		}
		
		return mds;
	}
}
