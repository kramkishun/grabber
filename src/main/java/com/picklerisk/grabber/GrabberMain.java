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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrabberMain {
	
    private static final Logger log = LoggerFactory.getLogger(GrabberMain.class);
	
	public static void main(String[] args) {
		System.out.println("Starting Endpoint Listener");
		//SpringApplication.run(GrabberMain.class, args);
		System.out.println("Endpoint Listener Started");
		new GrabberMain();
	}

	public GrabberMain( ) {
        RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        log.info(quote.toString());
        System.out.println(quote.toString());
	}
	
	// alpha vantage
	public void TestMongo() {
		MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
		MongoClient mongoClient = new MongoClient(connectionString);

		MongoDatabase database = mongoClient.getDatabase("stockData");
		
		MongoCollection<Document> allDocs = database.getCollection("daily");
		
		Document doc = new Document()
				.append("MSFT", new Document()
						.append("date", "01-03-2010")
						.append("open", 134.3)
						.append("close", 392.3));
		
		allDocs.insertOne(doc);
		mongoClient.close();
	}
}
