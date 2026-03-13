package com.alturion.agent.client;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.alturion.agent.common.ApiResponse;
import com.alturion.agent.dto.PolicyInfoSummaryDto;
import com.alturion.agent.exception.DependencyServiceUnavailableException;
import com.alturion.agent.exception.ResourceNotFoundException;

@Component
public class PolicyInfoClient {
	
	Logger logger = Logger.getLogger(PolicyInfoClient.class.getName());
	
	private final RestTemplate restTemplate;
	private final String baseUrl;
	
	public PolicyInfoClient(RestTemplate restTemplate, @Value("${alturion.policyinfo.service}") String baseUrl){
		
		this.restTemplate = restTemplate;
		this.baseUrl = baseUrl;
		}
	
	public List<PolicyInfoSummaryDto> getAllPoliciesDetails(List<Long> policyOwnerId) {
		
		logger.info("Executing PolicyInfoClient::getAllPoliciesDetails");
		String url = baseUrl + "?policyOwnerId=" +
						policyOwnerId.stream()
						.map(String::valueOf)
						.collect(Collectors.joining(","));
		
		logger.info("GET "+url);
		List<PolicyInfoSummaryDto> policySummaryList = new ArrayList<>();
		try {
			ResponseEntity<ApiResponse<List<PolicyInfoSummaryDto>>> 
			policyApiResponse = restTemplate.exchange(url, 
					HttpMethod.GET, 
					null, 
					new ParameterizedTypeReference<ApiResponse<List<PolicyInfoSummaryDto>>>() {} );
			logger.info("PolicyInfo Service Retuned with: "+ policyApiResponse.getStatusCode());
			policySummaryList = policyApiResponse.getBody().getData();
		}
		catch(HttpClientErrorException httpException) {
			if(httpException.getStatusCode()==HttpStatus.NOT_FOUND) {
				throw new ResourceNotFoundException("No policy Details Found");
			}
		}
		catch(ResourceAccessException resourceException) {
			throw new DependencyServiceUnavailableException("Dependent Service Not Available");
		}
		return policySummaryList;
		
	}

}
