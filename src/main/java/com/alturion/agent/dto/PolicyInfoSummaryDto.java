package com.alturion.agent.dto;

import com.alturion.agent.enums.PolicyCategory;
import com.alturion.agent.enums.PolicyStatus;
import com.alturion.agent.enums.PolicyTier;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PolicyInfoSummaryDto {
	
	private String policyNumber;
	private PolicyCategory policyCategory;
	private PolicyTier policyTier;
	private PolicyStatus policyStatus;
	private Long policyOwnerId;
	
	public PolicyInfoSummaryDto() {
		super();
	}
	
	public PolicyTier getPolicyTier() {
		return policyTier;
	}
	public void setPolicyTier(PolicyTier policyTier) {
		this.policyTier = policyTier;
	}
	public Long getPolicyOwnerId() {
		return policyOwnerId;
	}
	public void getPolicyOwnerId(Long policyOwnerId) {
		this.policyOwnerId = policyOwnerId;
	}
	public PolicyCategory getPolicyCategory() {
		return policyCategory;
	}
	public void setPolicyCategory(PolicyCategory policyCategory) {
		this.policyCategory = policyCategory;
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
