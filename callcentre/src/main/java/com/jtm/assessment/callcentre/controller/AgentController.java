package com.jtm.assessment.callcentre.controller;

import com.jtm.assessment.callcentre.database.entity.Team;
import com.jtm.assessment.callcentre.database.repo.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AgentController {

    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("/Basic/getTeams")
    public ResponseEntity getAllTeams(){
        List<Team>teams = teamRepository.findAll();
        return new ResponseEntity(teams, HttpStatus.OK);
    }

}
