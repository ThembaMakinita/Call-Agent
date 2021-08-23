package com.jtm.assessment.callcentre.database.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Team")
public class Team {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "Team_Managers",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "manager_id")
    )
    private List<Manager> managers = new ArrayList<>();

    public boolean addManager(Manager manager){
       if(managers.size() < 2){
            managers.add(manager);
           return true;
       }
       else{
           return false;
       }
    }

}
