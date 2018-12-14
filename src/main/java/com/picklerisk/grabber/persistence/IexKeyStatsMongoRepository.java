package com.picklerisk.grabber.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.picklerisk.grabber.JsonSchema.Iex.KeyStats;

public interface IexKeyStatsMongoRepository extends MongoRepository<KeyStats, String> {

}
