package com.klm.backend.klmbackend.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

	/**
	 * Build custom restTemplate bean with custom errorHandler
	 * @param builder
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
//		return builder.errorHandler(new RestTemplateErrorHandler()).build();
		return new RestTemplate();
	}
}
