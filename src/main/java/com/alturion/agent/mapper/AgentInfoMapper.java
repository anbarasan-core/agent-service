package com.alturion.agent.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.alturion.agent.domain.AgentInfo;
import com.alturion.agent.dto.AgentRequestDto;
import com.alturion.agent.dto.AgentResponseDto;

@Mapper(componentModel = "spring")
public interface AgentInfoMapper {
	
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	@Mapping(target = "agentStatus", ignore = true)
	@Mapping(target = "licenseNumber",ignore = true)
	@Mapping(target = "agentId",ignore=true)
	public AgentInfo toEntity(AgentRequestDto AgentRequestDto);
	
	public AgentResponseDto toResponseDto(AgentInfo agentInfo);
 
}
