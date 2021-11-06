package com.country_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.client"})
@SpringBootApplication
public class CountryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CountryServiceApplication.class, args);
	}

}
