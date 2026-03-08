package com.alturion.agent.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alturion.agent.domain.AgentInfo;

public interface AgentRepository extends JpaRepository<AgentInfo, Long>{
	
	public boolean existsByAadharNumber(String aadharNumber);
	public boolean existsByPanNumber(String panNumber);
	public boolean existsByEmail(String email);
	public Optional<AgentInfo> findByAgentIdAndLicenseNumber(Long agentId,String licenseNumber);
}
