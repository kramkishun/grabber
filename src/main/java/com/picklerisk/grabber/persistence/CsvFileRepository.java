package com.picklerisk.grabber.persistence;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

@Service
public class CsvFileRepository<E> implements PersistentStore<List<E>> {

	@Autowired
	private File file;

	private Logger log = LoggerFactory.getLogger(CsvFileRepository.class);
	
	public CsvFileRepository(File file) {
		this.file = file;
	}
	
	@Override
	public void clearData() {
		try {
			Files.delete(file.toPath());
		} catch (IOException e) {
			log.warn("Could not delete the file: " + file.getAbsolutePath());
		}
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public void addData(List<E> data) {
		
		try {
			CsvMapper mapper = new CsvMapper();
	
			if (data.size() > 0) {
				CsvSchema schema = mapper.schemaFor(data.get(0).getClass()).withHeader();
				for (E entry : data) {
					String csv = mapper.writer(schema).writeValueAsString(entry);
					Files.write(file.toPath(), csv.getBytes(), 
							StandardOpenOption.CREATE, StandardOpenOption.APPEND);
					
					// after first one we don't need the headers anymore.
					schema = mapper.schemaFor(entry.getClass());
				}

			} else {
				log.warn("Empty data found");
			}

		} catch (JsonProcessingException e) {
			log.warn("Unable to parse data to write to CSV file");
		} catch (IOException e) {
			log.warn("Unable to write to: " + file.getAbsolutePath());
			e.printStackTrace();
		} 
	}	
}
