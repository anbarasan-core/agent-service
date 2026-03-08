package com.alturion.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alturion.agent.domain.AgentInfo;

public interface AgentRepository extends JpaRepository<AgentInfo, Long>{
	
	boolean existsByAadharNumber(String aadharNumber);
	boolean existsByPanNumber(String panNumber);
	boolean existsByEmail(String email);
}
