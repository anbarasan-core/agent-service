package com.alturion.agent.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.alturion.agent.enums.AgentStatus;

public class AgentResponseDto {
	
	private String firstName;
	private String lastName;
	private String middleName;
	private String email;
	private String aadharNumber;
	private String panNumber;
	private String contactNumber;
	private LocalDate dateOfBirth;
	private String alternateContactNumber;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Long agentId;
	private String licenseNumber;
	private AgentStatus agentStatus;
	
	
	public AgentResponseDto() {
		super();
	}
	public AgentResponseDto(String firstName, String lastName, String middleName, String email, String aadharNumber,
			String panNumber, String contactNumber, LocalDate dateOfBirth, String alternateContactNumber,
			LocalDateTime createdAt, LocalDateTime updatedAt, Long agentId, String licenseNumber, AgentStatus agentStatus) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.email = email;
		this.aadharNumber = aadharNumber;
		this.panNumber = panNumber;
		this.contactNumber = contactNumber;
		this.dateOfBirth = dateOfBirth;
		this.alternateContactNumber = alternateContactNumber;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.agentId = agentId;
		this.licenseNumber = licenseNumber;
		this.agentStatus = agentStatus;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAlternateContactNumber() {
		return alternateContactNumber;
	}
	public void setAlternateContactNumber(String alternateContactNumber) {
		this.alternateContactNumber = alternateContactNumber;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
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
