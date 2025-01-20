package com.alura.challenge.foro.hub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	//	public static void main(String[] args) {
//			Flyway flyway = Flyway.configure()
//					.dataSource("jdbc:mysql://localhost:3306/vollmed_api", "root", ${SPRING_DATA_SOURCE)
//					.load();
//
//			//Repair the Flyway schema history
//			flyway.repair();
//
//			//Run migrations
//			flyway.migrate();
//
//		SpringApplication.run(Application.class, args);
//
//	}
}
