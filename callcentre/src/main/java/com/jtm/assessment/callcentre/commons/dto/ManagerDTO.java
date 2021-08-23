package com.jtm.assessment.callcentre.commons.dto;

import com.jtm.assessment.callcentre.database.entity.Team;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ManagerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String idNumber;
    private List<Team> teams = new ArrayList<>();
}
