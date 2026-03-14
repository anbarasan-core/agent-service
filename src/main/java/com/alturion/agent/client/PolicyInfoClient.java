package com.alturion.agent.client;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.alturion.agent.common.ApiResponse;
import com.alturion.agent.dto.PageResponseDto;
import com.alturion.agent.dto.PolicyInfoSummaryDto;
import com.alturion.agent.exception.DependencyServiceUnavailableException;

@Component
public class PolicyInfoClient {
	
	Logger logger = Logger.getLogger(PolicyInfoClient.class.getName());
	
	private final RestTemplate restTemplate;
	private final String baseUrl;
	private final String policyEndpoint;
	private final String policyPagesEndpoint;
	
	public PolicyInfoClient(RestTemplate restTemplate, @Value("${alturion.policyinfo.service}") String baseUrl, 
			 										   @Value("${alturion.policyinfo.service.owner.policies}") String policyEndpoint,
			 										  @Value("${alturion.policyinfo.service.owner.policypages}") String policyPagesEndpoint){
		
		this.restTemplate = restTemplate;
		this.baseUrl = baseUrl;
		this.policyEndpoint = policyEndpoint;
		this.policyPagesEndpoint = policyPagesEndpoint;
		}
	
	public List<PolicyInfoSummaryDto> getAllPoliciesDetails(List<Long> policyOwnerId) {
		
		logger.info("Executing PolicyInfoClient::getAllPoliciesDetails");
		String url = baseUrl + policyEndpoint + "?policyOwnerId=" +
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
			return policySummaryList;
		}
		catch(ResourceAccessException resourceException) {
			throw new DependencyServiceUnavailableException("Dependent Service Not Available");
		}
	}
	
	public PageResponseDto<PolicyInfoSummaryDto> getPoliciesByPages(List<Long> policyOwnerId,int page,int size) {
		
		logger.info("Executing PolicyInfoClient::getPoliciesByPages");
		String url = baseUrl + policyPagesEndpoint +"?policyOwnerId=" + policyOwnerId.stream()
												   .map(String::valueOf)
												   .collect(Collectors.joining(",")) + "&page=" + page + "&size=" +size;
		logger.info("GET "+url);
		try {
			ResponseEntity<ApiResponse<PageResponseDto<PolicyInfoSummaryDto>>> policyApiResponse
			= restTemplate.exchange(url, 
					HttpMethod.GET, 
					null, 
					new ParameterizedTypeReference<ApiResponse<PageResponseDto<PolicyInfoSummaryDto>>>() {});
			logger.info("PolicyService Returned with: "+policyApiResponse.getStatusCode());
			
			ApiResponse<PageResponseDto<PolicyInfoSummaryDto>> body = policyApiResponse.getBody();
			if(body == null || body.getData() == null){
	            return new PageResponseDto<>();
	        }
	        return body.getData();
		}
		catch(ResourceAccessException resourceException) {
			throw new DependencyServiceUnavailableException("Dependent Service Not Available");
		}
	}

}
