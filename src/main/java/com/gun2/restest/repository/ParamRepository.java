package com.gun2.restest.repository;

import com.gun2.restest.entity.JobParam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParamRepository extends JpaRepository<JobParam, Long> {
}