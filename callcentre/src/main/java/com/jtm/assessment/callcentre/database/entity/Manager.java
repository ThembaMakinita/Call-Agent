package com.jtm.assessment.callcentre.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Manager")
public class Manager {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String idNumber;

    @JsonIgnore
    @ManyToMany(mappedBy = "managers")
    private List<Team> teams = new ArrayList<>();

    public void addTeam(Team team){
        teams.add(team);
    }

    public void addMultipleTeams(List<Team> teams){
        for(Team team :teams){
            this.teams.add(team);
        }
    }
}
