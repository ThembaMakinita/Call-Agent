package com.jtm.assessment.callcentre.service;

import com.jtm.assessment.callcentre.database.entity.Agent;
import com.jtm.assessment.callcentre.database.entity.Manager;
import com.jtm.assessment.callcentre.database.entity.Team;
import com.jtm.assessment.callcentre.database.repo.AgentRepository;
import com.jtm.assessment.callcentre.database.repo.ManagerRepository;
import com.jtm.assessment.callcentre.database.repo.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class LoadTestData {
    @Autowired
    AgentRepository agentRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ManagerRepository managerRepository;

    @PostConstruct
    @Transactional
    public void execute() {

        Team team1 = createTeam("Marvel");
        Team team2 = createTeam("DC");
        Team team3 = createTeam("DBZ");
        List<Team>teams = Arrays.asList(team1,team2,team3);

        Manager  manager1 = createManager("Bob","Spilburg","9996565658588");
        Manager  manager2 = createManager("Tom","Johnson","9996565658588");

        team1.addManager(manager1);
        team2.addManager(manager1);
        team3.addManager(manager2);

        teamRepository.saveAll(teams);

        createAgent("Bruce", "Banner", "1011125190081", team1,manager1);
        createAgent("Tony", "Stark", "6912115191083", team1,manager1);
        createAgent("Peter", "Parker", "7801115190084", team1,manager1);
        createAgent("Bruce", "Wayne", "6511185190085", team2,manager1);
        createAgent("Clark", "Kent", "5905115190086",team3,manager2);
    }

    private Team createTeam(String name) {
        Team t = new Team();
        t.setName(name);
        return t;
    }

    private Agent createAgent(String firstName, String lastName, String idNumber, Team team,Manager manager) {
        Agent a = new Agent();
        a.setFirstName(firstName);
        a.setLastName(lastName);
        a.setIdNumber(idNumber);
        a.setTeam(team);
        a.setManager(manager);
        return agentRepository.save(a);
    }

    private Manager createManager(String firstName, String lastName, String idNumber){

        Manager manager = new Manager();
        manager.setFirstName(firstName);
        manager.setLastName(lastName);
        manager.setIdNumber(idNumber);
        return managerRepository.save(manager);
    }
}
