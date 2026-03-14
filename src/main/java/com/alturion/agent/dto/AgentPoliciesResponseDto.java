package com.alturion.agent.dto;

import java.util.List;

import com.alturion.agent.enums.AgentStatus;

public class AgentPoliciesResponseDto {
	
	private Long agentId;
	private String licenseNumber;
	private AgentStatus agentStatus;
	private int currentPage;
    private int totalPages;
    private long totalPolicies;
    List<PolicyInfoSummaryDto> policiesList;
	
	
	public AgentPoliciesResponseDto() {
		super();
	}
	
	public long getTotalPolicies() {
		return totalPolicies;
	}
	public void setTotalPolicies(long totalPolicies) {
		this.totalPolicies = totalPolicies;
	}
	public List<PolicyInfoSummaryDto> getPoliciesList() {
		return policiesList;
	}
	public void setPoliciesList(List<PolicyInfoSummaryDto> policiesList) {
		this.policiesList = policiesList;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public Long getAgentId() {
		return agentId;
	}
	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public AgentStatus getAgentStatus() {
		return agentStatus;
	}
	public void setAgentStatus(AgentStatus agentStatus) {
		this.agentStatus = agentStatus;
	}
}
