package com.alturion.agent.client;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.alturion.agent.common.ApiResponse;
import com.alturion.agent.dto.PolicyOwnerSummaryDto;
import com.alturion.agent.exception.DependencyServiceUnavailableException;

@Component
public class PolicyOwnerClient {
	
	Logger logger = Logger.getLogger(PolicyOwnerClient.class.getName());
	
	private final RestTemplate restTemplate;
	private final String baseUrl;

	public PolicyOwnerClient(RestTemplate restTemplate, @Value("${alturion.policyowner.service}") String baseUrl) {
		this.restTemplate = restTemplate;
		this.baseUrl = baseUrl;
	}
	
	public List<PolicyOwnerSummaryDto> getOwnerSummaryList(Long agentId) {
		
		logger.info("Executing PolicyOwnerClient::getOwnerSummaryList");
		String url = baseUrl + agentId;
		logger.info("GET "+url);
		List<PolicyOwnerSummaryDto> ownerSummaryList = new ArrayList<>();
		try {
			ResponseEntity<ApiResponse<List<PolicyOwnerSummaryDto>>> 
			ownerApiResponse = restTemplate.exchange(url, 
					HttpMethod.GET, 
					null, 
					new ParameterizedTypeReference<ApiResponse<List<PolicyOwnerSummaryDto>>>() {}
					);
			logger.info("PolicyOwner Service Retuned with: "+ownerApiResponse.getStatusCode()); 
			ownerSummaryList = ownerApiResponse.getBody().getData();
		}
		catch(ResourceAccessException resourceException) {
			throw new DependencyServiceUnavailableException("Dependent Service Not Available");
		}
		return ownerSummaryList;
		
	}
}
