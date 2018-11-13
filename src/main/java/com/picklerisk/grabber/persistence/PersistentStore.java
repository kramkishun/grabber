package com.picklerisk.grabber.persistence;

/**
 * Stores data of type/schema U in a location defined by persistence type T.
 * 
 * @author rhaps
 *
 * @param <T> Type of data store (mongo, flat file, etc.)
 * @param <U> Type of data to be stored (TimeSeriesDailyAdjusted, etc.)
 */

public interface PersistentStore<T, U> {

	public void clearData();
	public void addData(U data);
}
