package com.jtm.assessment.callcentre.controller;

import com.jtm.assessment.callcentre.database.entity.Agent;
import com.jtm.assessment.callcentre.database.entity.Team;
import com.jtm.assessment.callcentre.database.repo.AgentRepository;
import com.jtm.assessment.callcentre.database.repo.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class AgentController {

    @Autowired
    private AgentRepository agentRepository;

    @GetMapping("/Basic/getAgents")
    public ResponseEntity getAllTeams(){
        List<Agent> agents = agentRepository.findAll();
        return new ResponseEntity(agents, HttpStatus.OK);
    }

    @GetMapping("/Basic/getAgent/{TeamId}")
    public ResponseEntity getAllTeamById(@PathVariable(value ="TeamId") Long id){
        Optional<Agent> agent = agentRepository.findById(id);
        return new ResponseEntity(agent, HttpStatus.OK);
    }

}
