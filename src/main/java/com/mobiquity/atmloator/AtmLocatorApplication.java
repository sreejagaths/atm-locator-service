package com.mobiquity.atmloator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

import com.mobiquity.atmloator.model.response.ATMResponse;
import com.mobiquity.atmloator.serviceimpl.IntegrationUtils;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableCaching
//@EnableAsync
@EnableSwagger2
public class AtmLocatorApplication {
	
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	@Autowired
	IntegrationUtils integrationUtils;

	public static void main(String[] args) {
		SpringApplication.run(AtmLocatorApplication.class, args);
		AtmLocatorApplication app = new AtmLocatorApplication();
		//app.atmResponse();
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean("atmResponse")
	@EventListener(ApplicationReadyEvent.class)
	//@Async
	public ATMResponse atmResponse() {
		return integrationUtils.atmResponse();
	}

	@Bean
	public Docket api() {
	    return new Docket(DocumentationType.SWAGGER_2)
	            .select()
	            .apis(RequestHandlerSelectors.any())
	            .paths(PathSelectors.any())
	            .build();
	}
	
}
