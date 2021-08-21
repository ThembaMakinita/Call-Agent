package com.jtm.assessment.callcentre.database.repo;

import com.jtm.assessment.callcentre.database.entity.Manager;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
