package com.jtm.assessment.callcentre.database.repo;

import com.jtm.assessment.callcentre.database.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Long> {
}
