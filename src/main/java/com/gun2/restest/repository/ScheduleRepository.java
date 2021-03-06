package com.gun2.restest.repository;

import com.gun2.restest.entity.Schedule;
import com.gun2.restest.repository.custom.ScheduleCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>, ScheduleCustomRepository {

    List<Schedule> findByRun(boolean run);
}