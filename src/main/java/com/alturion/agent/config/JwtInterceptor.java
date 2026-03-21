package com.alturion.agent.config;

import java.io.IOException;

import org.jboss.logging.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtInterceptor implements ClientHttpRequestInterceptor{
	
	private static Logger logger = Logger.getLogger(JwtInterceptor.class);
	
	private final HttpServletRequest httpServletRequest;

    public JwtInterceptor(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }
    
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		
		logger.info("Executing JwtInterceptor::intercept");
		String authHeader = httpServletRequest.getHeader("Authorization");
		
		if(authHeader!=null && authHeader.startsWith("Bearer ")) {
			request.getHeaders().set("Authorization", authHeader);
			logger.info(">>> Forwarding JWT: " +authHeader);
		}
		return execution.execute(request, body);
	}

}
