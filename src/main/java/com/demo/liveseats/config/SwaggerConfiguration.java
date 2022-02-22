package com.demo.liveseats.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {
	@Autowired
	private Docket swaggerSpringfoxApiDocket;

	@PostConstruct
	private void configure() {
		swaggerSpringfoxApiDocket.useDefaultResponseMessages(false);
	}
}