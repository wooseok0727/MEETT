package com.team.meett.repository;

import com.team.meett.model.TeamSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TeamScheduleRepository extends JpaRepository<TeamSchedule, Long> {

    public List<TeamSchedule> findByUsername(String username);

}
