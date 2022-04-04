package com.example.GDS.mini.project;

import com.example.GDS.mini.project.Exceptions.InvalidNumberOfColumnsException;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class GdsMiniProjectApplication {


	public static void main(String[] args) throws IOException, InvalidNumberOfColumnsException,
			CsvValidationException {
		ConfigurableApplicationContext context = SpringApplication.run(GdsMiniProjectApplication.class, args);
		UserCsvParser csvParser = context.getBean(UserCsvParser.class);
		UserDataStore dataStore = context.getBean(UserDataStoreImpl.class);
		List<User> seedData = csvParser.parseCsv(new FileInputStream(args[0]));
		dataStore.seedData(seedData);
	}

}
