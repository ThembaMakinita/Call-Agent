package com.jtm.assessment.callcentre.controller;

import com.jtm.assessment.callcentre.database.entity.Agent;
import com.jtm.assessment.callcentre.database.entity.Manager;
import com.jtm.assessment.callcentre.database.entity.Team;
import com.jtm.assessment.callcentre.database.repo.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/Basic/getTeams")
    public ResponseEntity getAllTeams(){
        List<Team> teams = teamRepository.findAll();
        return new ResponseEntity(teams, HttpStatus.OK);
    }

    @GetMapping("/Basic/getTeam/{TeamId}")
    public ResponseEntity getAllTeamById(@PathVariable(value ="TeamId") Long id){
        Optional<Team> teams = teamRepository.findById(id);
        return new ResponseEntity(teams, HttpStatus.OK);
    }

    @PostMapping("/Basic/team")
    public ResponseEntity createAgent(@RequestBody Team team){

        if(team != null)
            return new ResponseEntity(teamRepository.save(team), HttpStatus.OK);
        return new ResponseEntity<>("Unable to create Team", HttpStatus.BAD_REQUEST);
    }

}
