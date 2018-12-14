package com.picklerisk.grabber.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.picklerisk.grabber.JsonSchema.Iex.News;

public interface IexNewsMongoRepository extends MongoRepository<News, String> {

}
