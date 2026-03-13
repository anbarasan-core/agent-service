package com.alturion.agent.dto;

public class AgentDashboardDto {
	
	private Long agentId;
	private String licenseNumber;
	private String agentName;
	private int totalOwners;
	private int totalPolicies;
	private Long activePolicies;
	private Long expiredPolicies;
	
	public AgentDashboardDto() {
		super();
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
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public int getTotalOwners() {
		return totalOwners;
	}
	public void setTotalOwners(int totalOwners) {
		this.totalOwners = totalOwners;
	}
	public int getTotalPolicies() {
		return totalPolicies;
	}
	public void setTotalPolicies(int totalPolicies) {
		this.totalPolicies = totalPolicies;
	}
	public Long getActivePolicies() {
		return activePolicies;
	}
	public void setActivePolicies(Long activePolicies) {
		this.activePolicies = activePolicies;
	}
	public Long getExpiredPolicies() {
		return expiredPolicies;
	}
	public void setExpiredPolicies(Long expiredPolicies) {
		this.expiredPolicies = expiredPolicies;
	}
}
