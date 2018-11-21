package com.picklerisk.grabber.JsonSchema.Iex;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TimeSeriesDailyAdjustedTest {

	@Test
	public void test() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		final String url = "https://api.iextrading.com/1.0/stock/aapl/chart/5y";

		ObjectMapper mapper = new ObjectMapper();
		try {
			TimeSeriesDailyAdjustedEntry[] data = mapper.readValue(new URL(url), TimeSeriesDailyAdjustedEntry[].class);
			assertNotNull(data);
		} catch (Exception e) { 
			e.printStackTrace();
			fail();
		}	
	}
}
