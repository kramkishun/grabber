package com.picklerisk.grabber;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
