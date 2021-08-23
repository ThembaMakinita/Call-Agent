package com.jtm.assessment.callcentre.service;

import com.jtm.assessment.callcentre.database.entity.Agent;
import com.jtm.assessment.callcentre.database.model.AgentPage;
import com.jtm.assessment.callcentre.database.model.AgentSearchCriteria;
import com.jtm.assessment.callcentre.database.repo.AgentCriteriaRepository;
import com.jtm.assessment.callcentre.database.repo.AgentRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    private final AgentRepository agentRepository;
    private final AgentCriteriaRepository agentCriteriaRepository;

    public AgentService(AgentRepository agentRepository, AgentCriteriaRepository agentCriteriaRepository) {
        this.agentRepository = agentRepository;
        this.agentCriteriaRepository = agentCriteriaRepository;
    }

    public Page<Agent> getAgents(AgentPage agentPage, AgentSearchCriteria agentSearchCriteria){
        return agentCriteriaRepository.findAllWithFilters(agentPage,agentSearchCriteria);
    }
}
