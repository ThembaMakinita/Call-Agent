package com.jtm.assessment.callcentre.database.model;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class AgentPage {
    private int pageNumber =0;
    private int pageSize = 1 ;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortBy = "lastName";
}
