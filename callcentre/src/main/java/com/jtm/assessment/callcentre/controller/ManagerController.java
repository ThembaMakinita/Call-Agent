package com.jtm.assessment.callcentre.controller;

import com.jtm.assessment.callcentre.database.entity.Manager;
import com.jtm.assessment.callcentre.database.entity.Team;
import com.jtm.assessment.callcentre.database.repo.ManagerRepository;
import com.jtm.assessment.callcentre.database.repo.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ManagerController {

    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private TeamRepository teamRepository;

    @PostMapping("/Advance/manager")
    public ResponseEntity createManager(@RequestBody Manager manager){
        int numAddedTeams = 0;
        Optional<Team> tempTeam;
        if(manager != null)
            if(manager.getTeams().size() > 0) {
                for(Team team : manager.getTeams()){
                   tempTeam = teamRepository.findById(team.getId());
                   if(tempTeam != null){
                       if (tempTeam.get().getManagers().size() < 2)
                       {
                           tempTeam.get().getManagers().add(manager);
                           teamRepository.save(tempTeam.get());
                           numAddedTeams++;
                       }
                       else{
                           //unable to add manager to that team.
                          return new ResponseEntity<>("Unable to create Manager, Allocated team does not have the capacity", HttpStatus.BAD_REQUEST);
                       }
                   }
                }
                return new ResponseEntity(managerRepository.save(manager), HttpStatus.OK);
            }
        return new ResponseEntity<>("Unable to create Manager", HttpStatus.BAD_REQUEST);
    }
}
