package com.raaji.eventmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@SpringBootApplication
public class EventmanagementApplication {

	//main method
	public static void main(String[] args) {
		SpringApplication.run(EventmanagementApplication.class, args);
		System.out.println("Event Management Application Started");
	}

}
