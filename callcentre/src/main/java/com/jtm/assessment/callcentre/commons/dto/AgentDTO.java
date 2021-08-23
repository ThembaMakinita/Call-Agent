package com.jtm.assessment.callcentre.commons.dto;

import com.jtm.assessment.callcentre.database.entity.Manager;
import com.jtm.assessment.callcentre.database.entity.Team;
import lombok.Data;

@Data
public class AgentDTO {

    private String firstName;
    private String lastName;
    private String idNumber;
    private String teamName;
    private String managersLastName;
}
