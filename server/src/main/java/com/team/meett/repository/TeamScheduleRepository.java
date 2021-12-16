package com.team.meett.repository;

import com.team.meett.model.TeamSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TeamScheduleRepository extends JpaRepository<TeamSchedule, Long> {

    public TeamSchedule findBySeq(Long seq);
    public List<TeamSchedule> findByUsername(String username);
    public List<TeamSchedule> findByTeam_id(String teamId);

    public List<TeamSchedule> findByTitleContains(String title);
    public List<TeamSchedule> findByTitle(String title);
    public List<TeamSchedule> findByDetailContains(String details);
}
