package com.picklerisk.grabber.JsonSchema.AlphaVantage;

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
	public void testSchema() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		final String url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=MSFT&outputsize=full&apikey=demo";
		final double eps = 0.001;
		
		ObjectMapper mapper = new ObjectMapper();
		TimeSeriesDailyAdjusted data = mapper.readValue(new URL(url), TimeSeriesDailyAdjusted.class);
		
		assertEquals(data.metaData.symbol, "MSFT");
		assertEquals(data.allDailyEntries.entries.get("2018-11-09").close, 109.5700, eps);
		assertEquals(data.allDailyEntries.entries.get("1998-01-02").close, 131.1300, eps);
	}
}
