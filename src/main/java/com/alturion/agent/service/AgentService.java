package com.alturion.agent.service;

import com.alturion.agent.dto.AgentRequestDto;
import com.alturion.agent.dto.AgentResponseDto;

public interface AgentService {
	
	public AgentResponseDto createAgent(AgentRequestDto agentRequestDto);

}
