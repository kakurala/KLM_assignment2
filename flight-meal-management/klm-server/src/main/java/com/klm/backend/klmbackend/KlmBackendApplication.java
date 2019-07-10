package com.klm.backend.klmbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
	    SecurityAutoConfiguration.class})

public class KlmBackendApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(KlmBackendApplication.class, args);
	}
}
