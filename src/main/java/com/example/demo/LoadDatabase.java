package com.example.demo;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(EmployeeRepository repository) {

		return args -> {
			log.info("Preloading " + repository.save(new Employee("Marco", "Gabaglio",
					Arrays.asList("via vallemaggia 6600 Locarno", "via san gottardo solduno"),
					LocalDate.of(1991, Month.MAY, 22), Arrays.asList("077419893", "+41795630954"),
					Files.readAllBytes(new File("gufo.jpg").toPath()))));

			log.info("Preloading " + repository.save(new Employee("Pinco", "Pallino",
					Arrays.asList("via vallemaggia 6600 Locarno", "via san gottardo solduno"),
					LocalDate.of(1991, Month.MAY, 22), Arrays.asList("077419893", "+41795630954"),
					Files.readAllBytes(new File("tigre.jpg").toPath()))));
//			log.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief")));
		};
	}
}