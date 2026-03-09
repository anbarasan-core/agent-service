package com.alturion.agent.service;

import com.alturion.agent.dto.AgentRequestDto;
import com.alturion.agent.dto.AgentResponseDto;

public interface AgentService {
	
	public AgentResponseDto createAgent(AgentRequestDto agentRequestDto);
	public AgentResponseDto fetchAgent(Long agentId,String licenseNumber);
	public void deactivateAgent(Long agentId,String licenseNumber);

}
