package com.picklerisk.grabber.persistence;

/**
 * Stores data of type/schema U in a location defined by persistence type T.
 * 
 * @author rhaps
 *
 * @param <T> Type of data to be stored (TimeSeriesDailyAdjusted, etc.)
 */

public interface PersistentStore<T> {

	// TODO: [Priority-1] Abstract the repository into interface
	// TODO: [Priority-1] Add CSV/flat file persistent store
	public void clearData();
	public void addData(T data);
}
