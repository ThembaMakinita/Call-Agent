package com.jtm.assessment.callcentre.controller;

import com.jtm.assessment.callcentre.database.entity.Agent;
import com.jtm.assessment.callcentre.database.entity.Manager;
import com.jtm.assessment.callcentre.database.entity.Team;
import com.jtm.assessment.callcentre.database.repo.AgentRepository;
import com.jtm.assessment.callcentre.database.repo.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AgentController {

    @Autowired
    private AgentRepository agentRepository;

    @GetMapping("/Basic/agents")
    public ResponseEntity getAllTeams(){
        List<Agent> agents = agentRepository.findAll();
        return new ResponseEntity(agents, HttpStatus.OK);
    }

    @GetMapping("/Basic/agent/{TeamId}")
    public ResponseEntity getAllTeamById(@PathVariable(value ="TeamId") Long id){
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

}
