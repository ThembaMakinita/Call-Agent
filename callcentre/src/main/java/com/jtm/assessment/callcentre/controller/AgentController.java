package com.jtm.assessment.callcentre.controller;

import com.jtm.assessment.callcentre.database.entity.Agent;
import com.jtm.assessment.callcentre.database.entity.Manager;
import com.jtm.assessment.callcentre.database.entity.Team;
import com.jtm.assessment.callcentre.database.model.AgentPage;
import com.jtm.assessment.callcentre.database.model.AgentSearchCriteria;
import com.jtm.assessment.callcentre.database.repo.AgentRepository;
import com.jtm.assessment.callcentre.database.repo.TeamRepository;
import com.jtm.assessment.callcentre.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AgentController {

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private AgentService agentService;

    @GetMapping("/Basic/agents")
    public ResponseEntity getAllAgents(){
        List<Agent> agents = agentRepository.findAll();
        return new ResponseEntity(agents, HttpStatus.OK);
    }

    @GetMapping("/Basic/agent/{agentId}")
    public ResponseEntity getAllAgentById(@PathVariable(value ="agentId") Long id){
        Optional<Agent> agent = agentRepository.findById(id);
        if (agent != null)
        return new ResponseEntity(agent, HttpStatus.OK);
        return new ResponseEntity<>("No Results Found For Agents", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/Basic/agent")
    public ResponseEntity createAgent(@RequestBody Agent agent){

        if(agent.getFirstName()!=null && agent.getTeam() != null){
            for(Manager manager :agent.getTeam().getManagers()){
                if(manager.equals(agent.getManager())){
                    return new ResponseEntity(agentRepository.save(agent), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>("Unable to create Agent", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("Advanced/agents")
    public ResponseEntity<Page<Agent>> getAgentsByPagination(AgentPage agentPage, AgentSearchCriteria agentSearchCriteria){
        return new ResponseEntity<>(agentService.getAgents(agentPage,agentSearchCriteria),HttpStatus.OK);
    }

}
