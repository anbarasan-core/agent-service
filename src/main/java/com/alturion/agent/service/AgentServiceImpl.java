package com.alturion.agent.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.alturion.agent.domain.AgentInfo;
import com.alturion.agent.dto.AgentRequestDto;
import com.alturion.agent.dto.AgentResponseDto;
import com.alturion.agent.enums.AgentStatus;
import com.alturion.agent.exception.DuplicateUserException;
import com.alturion.agent.mapper.AgentInfoMapper;
import com.alturion.agent.repository.AgentRepository;

@Service
public class AgentServiceImpl implements AgentService{
	
	private final AgentRepository agentRepository;
	private final AgentInfoMapper agentInfoMapper;
	
	public AgentServiceImpl(AgentRepository agentRepository,AgentInfoMapper agentInfoMapper){
		this.agentRepository = agentRepository;
		this.agentInfoMapper = agentInfoMapper;
	}

	@Override
	public AgentResponseDto createAgent(AgentRequestDto agentRequestDto) {
		
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

}
