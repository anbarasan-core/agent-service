package com.alturion.agent.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alturion.agent.common.ApiResponse;
import com.alturion.agent.dto.AgentDashboardDto;
import com.alturion.agent.dto.AgentRequestDto;
import com.alturion.agent.dto.AgentResponseDto;
import com.alturion.agent.service.AgentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/agent")
public class AgentController {
	
	private final AgentService agentService;
	
	public AgentController(AgentService agentService) {
		this.agentService = agentService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<ApiResponse<AgentResponseDto>> agentCreation(@Valid @RequestBody AgentRequestDto agentRequestDto) {
		
		AgentResponseDto agentResponse = agentService.createAgent(agentRequestDto);
		ApiResponse<AgentResponseDto> agentApiResponse = new ApiResponse<>(
				LocalDateTime.now(),
				HttpStatus.CREATED.value(),
				"Agent Record Created Successfully",
				agentResponse
				);
		return new ResponseEntity<ApiResponse<AgentResponseDto>>(agentApiResponse,HttpStatus.CREATED);
	}
	
	@GetMapping("/{agentId}/{licenseNumber}")
	public ResponseEntity<ApiResponse<AgentResponseDto>> fetchingAgent(@PathVariable Long agentId, @PathVariable String licenseNumber){
		
		AgentResponseDto agentResponse = agentService.fetchAgent(agentId, licenseNumber);
		ApiResponse<AgentResponseDto> agentApiResponse = new ApiResponse<>(
				LocalDateTime.now(),
				HttpStatus.OK.value(),
				"Agent Record Fetched Successfully",
				agentResponse
				);
		return new ResponseEntity<ApiResponse<AgentResponseDto>>(agentApiResponse,HttpStatus.OK);
	}
	
	@PatchMapping("/{agentId}/{licenseNumber}/deactivate")
	public ResponseEntity<ApiResponse<Void>> deactivatingAgent(@PathVariable Long agentId,@PathVariable String licenseNumber) {
		
		agentService.deactivateAgent(agentId, licenseNumber);
		ApiResponse<Void> agentApiResponse = new ApiResponse<>(
				LocalDateTime.now(),
				HttpStatus.OK.value(),
				"Agent Deactivated Successfully",
				null
				);
		return new ResponseEntity<ApiResponse<Void>>(agentApiResponse,HttpStatus.OK);
	}
	
	@GetMapping("/{agentId}/{licenseNumber}/dashboard")
	public ResponseEntity<ApiResponse<AgentDashboardDto>> agentDashboard(@PathVariable Long agentId,@PathVariable String licenseNumber) {
		
		AgentDashboardDto agentDashboardDto = agentService.aggregateAgentDashboard(agentId, licenseNumber);
		ApiResponse<AgentDashboardDto> agentApiResponse = new ApiResponse<>(
				LocalDateTime.now(),
				HttpStatus.OK.value(),
				"Agent Dashboard Details Fetched Successfully",
				agentDashboardDto
				);
		return new ResponseEntity<ApiResponse<AgentDashboardDto>>(agentApiResponse,HttpStatus.OK);
	}

}
