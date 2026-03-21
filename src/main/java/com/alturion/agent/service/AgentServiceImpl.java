package com.alturion.agent.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

import com.alturion.agent.client.PolicyInfoClient;
import com.alturion.agent.client.PolicyOwnerClient;
import com.alturion.agent.domain.AgentInfo;
import com.alturion.agent.dto.AgentDashboardDto;
import com.alturion.agent.dto.AgentPoliciesResponseDto;
import com.alturion.agent.dto.AgentRequestDto;
import com.alturion.agent.dto.AgentResponseDto;
import com.alturion.agent.dto.PageResponseDto;
import com.alturion.agent.dto.PolicyInfoSummaryDto;
import com.alturion.agent.dto.PolicyOwnerSummaryDto;
import com.alturion.agent.enums.AgentStatus;
import com.alturion.agent.enums.PolicyStatus;
import com.alturion.agent.exception.DuplicateUserException;
import com.alturion.agent.exception.InvalidAgentTransactionException;
import com.alturion.agent.exception.ResourceNotFoundException;
import com.alturion.agent.mapper.AgentInfoMapper;
import com.alturion.agent.repository.AgentRepository;

@Service
public class AgentServiceImpl implements AgentService{
	
	private static Logger logger = Logger.getLogger(AgentServiceImpl.class);
	
	private final AgentRepository agentRepository;
	private final AgentInfoMapper agentInfoMapper;
	private final PolicyOwnerClient policyOwnerClient;
	private final PolicyInfoClient policyInfoClient;
	
	public AgentServiceImpl(AgentRepository agentRepository,AgentInfoMapper agentInfoMapper,PolicyOwnerClient policyOwnerClient,PolicyInfoClient policyInfoClient){
		this.agentRepository = agentRepository;
		this.agentInfoMapper = agentInfoMapper;
		this.policyOwnerClient = policyOwnerClient;
		this.policyInfoClient = policyInfoClient;
	}

	@Override
	public AgentResponseDto createAgent(AgentRequestDto agentRequestDto) {
		
		logger.info("Executing AgentServiceImpl::createAgent");
		
		if(agentRepository.existsByAadharNumber(agentRequestDto.getAadharNumber())) {
			throw new DuplicateUserException("Aadhar already exists - User already present");
		}
		if(agentRepository.existsByPanNumber(agentRequestDto.getPanNumber())) {
			throw new DuplicateUserException("Pan already exists - User already present");
		}
		if(agentRepository.existsByEmail(agentRequestDto.getEmail())) {
		    throw new DuplicateUserException("Email already exists - User already present");
		}
		
		AgentInfo agentDetails = agentInfoMapper.toEntity(agentRequestDto);
		agentDetails.setCreatedAt(LocalDateTime.now());
		agentDetails.setUpdatedAt(LocalDateTime.now());
		agentDetails.setAgentStatus(AgentStatus.ACTIVE);
		AgentInfo savedAgent = agentRepository.save(agentDetails);
		AgentResponseDto agentResponseDto = agentInfoMapper.toResponseDto(savedAgent);
		
		return agentResponseDto;
	}

	@Override
	public AgentResponseDto fetchAgent(Long agentId, String licenseNumber) {
		
		logger.info("Executing AgentServiceImpl::fetchAgent");
		AgentInfo agentDetails = agentRepository.findByAgentIdAndLicenseNumber(agentId, licenseNumber)
												.orElseThrow(()->new ResourceNotFoundException("No Agent Details Found"));
		AgentResponseDto agentResponseDto = agentInfoMapper.toResponseDto(agentDetails);
		return agentResponseDto;
	}

	@Override
	public void deactivateAgent(Long agentId, String licenseNumber) {
		
		logger.info("Executing AgentServiceImpl::deactivateAgent");
		AgentInfo agentDetails = agentRepository.findByAgentIdAndLicenseNumber(agentId, licenseNumber)
				.orElseThrow(()->new ResourceNotFoundException("No Agent Details Found"));
		if(agentDetails.getAgentStatus() == AgentStatus.ACTIVE) {
			agentDetails.setAgentStatus(AgentStatus.TERMINATED);
			agentDetails.setUpdatedAt(LocalDateTime.now());
		}
		else {
			throw new InvalidAgentTransactionException("Agent is NOT active. Unable to deactivate");
		}
		agentRepository.save(agentDetails);
		logger.info("Agent Successfully Terminated");
	}

	@Override
	public AgentDashboardDto aggregateAgentDashboard(Long agentId, String licenseNumber) {
		
		logger.info("Executing AgentServiceImpl::aggregateAgentDashboard");
		AgentDashboardDto dashboardDto = new AgentDashboardDto();
		AgentInfo agentDetails = agentRepository.findByAgentIdAndLicenseNumber(agentId, licenseNumber)
				.orElseThrow(()->new ResourceNotFoundException("No Agent Details Found"));
		dashboardDto.setAgentId(agentId);
		dashboardDto.setLicenseNumber(licenseNumber);
		String agentName = Stream.of(agentDetails.getFirstName(),agentDetails.getMiddleName(),agentDetails.getLastName())
				.filter(Objects::nonNull)
				.collect(Collectors.joining(" "));
		dashboardDto.setAgentName(agentName);
		
		List<PolicyOwnerSummaryDto> ownerSummary = policyOwnerClient.getOwnerSummaryList(agentId);
		dashboardDto.setTotalOwners(ownerSummary.size()); 
		
		List<Long> ownerIds = ownerSummary.stream()
		.map(PolicyOwnerSummaryDto::getUserId)
		.collect(Collectors.toList());
		
		if(ownerIds.isEmpty()) {
			dashboardDto.setTotalPolicies(0);
			dashboardDto.setActivePolicies(0L);
			dashboardDto.setActivePolicies(0L);
			dashboardDto.setExpiredPolicies(0L);
			return dashboardDto;
			
		}
		else {
		List<PolicyInfoSummaryDto> policiesSummary = policyInfoClient.getAllPoliciesDetails(ownerIds);
		dashboardDto.setTotalPolicies(policiesSummary.size());
		
		dashboardDto.setActivePolicies(policiesSummary.stream()
				.filter(n->n.getPolicyStatus()==PolicyStatus.ACTIVE)
				.count()); 
		dashboardDto.setExpiredPolicies(policiesSummary.stream()
				.filter(n->n.getPolicyStatus()==PolicyStatus.EXPIRED)
				.count()); 
		return dashboardDto;
		}
	}

	@Override
	public AgentPoliciesResponseDto getAllPoliciesByAgent(Long agentId, String licenseNumber,int page,int size) {
		
		logger.info("Executing AgentServiceImpl::getAllPoliciesByAgent");
		AgentPoliciesResponseDto responseDto = new AgentPoliciesResponseDto();
		AgentInfo agentDetails = agentRepository.findByAgentIdAndLicenseNumber(agentId, licenseNumber)
				.orElseThrow(()->new ResourceNotFoundException("No Agent Details Found"));
		responseDto.setAgentId(agentId);
		responseDto.setLicenseNumber(licenseNumber);
		responseDto.setAgentStatus(agentDetails.getAgentStatus());
		
		List<PolicyOwnerSummaryDto> ownerSummary = policyOwnerClient.getOwnerSummaryList(agentId);
		List<Long> ownerIds = ownerSummary.stream()
		.map(PolicyOwnerSummaryDto::getUserId)
		.collect(Collectors.toList());
		
		if(ownerIds.isEmpty()) {
			responseDto.setCurrentPage(page);
			responseDto.setTotalPages(0);
			responseDto.setPoliciesList(List.of());
			return responseDto;
		}
		
		PageResponseDto<PolicyInfoSummaryDto> allPolicyPages = policyInfoClient.getPoliciesByPages(ownerIds, page, size);
		responseDto.setCurrentPage(allPolicyPages.getCurrentPage());
		responseDto.setTotalPages(allPolicyPages.getTotalPage());
		responseDto.setPoliciesList(allPolicyPages.getContent());
		responseDto.setTotalPolicies(allPolicyPages.getTotalElements());
		return responseDto;
	}

}
