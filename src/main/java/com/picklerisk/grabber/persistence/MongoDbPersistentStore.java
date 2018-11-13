package com.picklerisk.grabber.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public class MongoDbPersistentStore<T extends MongoRepository<U, ?>, U> implements PersistentStore<T, U> {
	
	@Autowired
	private T repository;
	
	@Override
	public void clearData() {
		repository.deleteAll();
		
	}

	@Override
	public void addData(U data) {
		repository.save(data);
	}	
}
