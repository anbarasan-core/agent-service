package com.alturion.agent.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class AgentRequestDto {
	
	private String firstName;
	private String lastName;
	private String middleName;
	@Email(message = "Email ID not valid")
	@NotBlank(message = "Email ID cannot be empty")
	private String email;
	@NotBlank
	@NotBlank(message = "Aadhar No. cannot be empty")
	@Pattern(regexp = "^[0-9]{12}$", message = "Invalid Aadhar number")
	private String aadharNumber;
	@NotBlank
	@NotBlank(message = "Pan No. cannot be empty")
	@Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$", message = "Invalid PAN number")
	private String panNumber;
	private String contactNumber;
	private LocalDate dateOfBirth;
	private String alternateContactNumber;
	
	public AgentRequestDto() {
		super();
	}
	
	public AgentRequestDto(String firstName, String lastName, String middleName, String email,
			String aadharNumber, String panNumber, String contactNumber, LocalDate dateOfBirth,
			String alternateContactNumber) {
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
}
