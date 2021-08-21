package com.jtm.assessment.callcentre.database.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Agent")
public class Agent {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String idNumber;
    @ManyToOne
    private Team team;
    //@ManyToOne
   // private Manager manager;
}
