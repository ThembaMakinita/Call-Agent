package com.jtm.assessment.callcentre.database.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Team {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    //private Manager manager;

}
