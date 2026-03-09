package com.alturion.agent.service;

import java.time.LocalDateTime;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

import com.alturion.agent.domain.AgentInfo;
import com.alturion.agent.dto.AgentRequestDto;
import com.alturion.agent.dto.AgentResponseDto;
import com.alturion.agent.enums.AgentStatus;
import com.alturion.agent.exception.DuplicateUserException;
import com.alturion.agent.exception.InvalidAgentTransactionException;
import com.alturion.agent.exception.ResourceNotFoundException;
import com.alturion.agent.mapper.AgentInfoMapper;
import com.alturion.agent.repository.AgentRepository;

@Service
public class AgentServiceImpl implements AgentService{
	
	Logger logger = Logger.getLogger(AgentServiceImpl.class);
	
	private final AgentRepository agentRepository;
	private final AgentInfoMapper agentInfoMapper;
	
	public AgentServiceImpl(AgentRepository agentRepository,AgentInfoMapper agentInfoMapper){
		this.agentRepository = agentRepository;
		this.agentInfoMapper = agentInfoMapper;
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

}
