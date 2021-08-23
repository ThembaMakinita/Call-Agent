package com.jtm.assessment.callcentre.commons.dto;

import com.jtm.assessment.callcentre.database.entity.Manager;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class TeamDTO {
    private Long id;
    private String name;
    private List<Manager> managers = new ArrayList<>();
}
