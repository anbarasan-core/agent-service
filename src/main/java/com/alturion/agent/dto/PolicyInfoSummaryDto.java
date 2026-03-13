package com.alturion.agent.dto;

import com.alturion.agent.enums.PolicyStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PolicyInfoSummaryDto {
	
	private String policyNumber;
	private PolicyStatus policyStatus;
	
	public PolicyInfoSummaryDto() {
		super();
	}
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	public PolicyStatus getPolicyStatus() {
		return policyStatus;
	}
	public void setPolicyStatus(PolicyStatus policyStatus) {
		this.policyStatus = policyStatus;
	}
	
	

}
