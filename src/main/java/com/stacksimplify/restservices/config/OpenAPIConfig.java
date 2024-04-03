package com.stacksimplify.restservices.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
	
	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder().group("api").packagesToScan("com.stacksimplify.restservices.controllers").pathsToMatch("/users/**").build();
	}
	

}
