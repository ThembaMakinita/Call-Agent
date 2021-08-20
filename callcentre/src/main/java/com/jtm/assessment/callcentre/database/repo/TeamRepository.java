package com.jtm.assessment.callcentre.database.repo;

import com.jtm.assessment.callcentre.database.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
