package com.alturion.agent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateConfig {
	
	@Bean
    RestTemplate restTemplate(JwtInterceptor jwtInterceptor) {
    	RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(jwtInterceptor);
		return restTemplate;
	}

}
