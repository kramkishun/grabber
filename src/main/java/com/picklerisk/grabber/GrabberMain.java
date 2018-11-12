package com.picklerisk.grabber;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import com.picklerisk.grabber.JsonSchema.AlphaVantage.TimeSeriesDailyAdjusted;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrabberMain implements CommandLineRunner {
	
    private static final Logger log = LoggerFactory.getLogger(GrabberMain.class);
    
    @Autowired
    private MongoDbAdapter mongoAdapter;
    
    @Autowired
    private AlphaVantageGrabber grabber;
    
	public static void main(String[] args) {
		System.out.println("Start Main");
		SpringApplication.run(GrabberMain.class, args);
		System.out.println("End Main");
	}

	public GrabberMain( ) {
		
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Start Run");
		mongoAdapter.addTimeSeriesData(grabber.grab());
		System.out.println("End Run");
	}
}
