package com.country_service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import com.client.Client;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;

@ActiveProfiles("test")
@SpringBootTest
class CountryServiceApplicationTests {

    @Autowired
    private ApplicationContext context;

	@Test
	void contextLoads() {
	}

    @Test
    void whenContextLoads_thenRunnersAreNotLoaded() {
        assertThrows(NoSuchBeanDefinitionException.class, 
          () -> context.getBean(Client.class), 
          "CommandLineRunner should not be loaded during this integration test");
	}
	
}
