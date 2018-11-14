package com.picklerisk.grabber.persistence;

/**
 * Stores data of type/schema U in a location defined by persistence type T.
 * 
 * @author rhaps
 *
 * @param <T> Type of data to be stored (TimeSeriesDailyAdjusted, etc.)
 */

public interface PersistentStore<T> {

	public void clearData();
	public void addData(T data);
}
