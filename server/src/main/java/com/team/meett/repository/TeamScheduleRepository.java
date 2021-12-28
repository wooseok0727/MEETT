package com.team.meett.repository;

import com.team.meett.model.TeamSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;


public interface TeamScheduleRepository extends JpaRepository<TeamSchedule, Long> {

    public List<TeamSchedule> findByUsername(String username);
    public List<TeamSchedule> findByTeam_id(String teamId);

    public List<TeamSchedule> findByTitleContainsAndTeam_id(String title, String team_id);
    public List<TeamSchedule> findByTitleAndTeam_id(String title, String team_id);
    public List<TeamSchedule> findByDetailContainsAndTeam_id(String details, String team_id);

    public List<TeamSchedule> findByTeam_idAndTitleContains(String teamId, String title);
    public List<TeamSchedule> findByTeam_idAndTitle(String teamId, String title);
    public List<TeamSchedule> findByTeam_idAndDetailContains(String teamId, String details);
    public List<TeamSchedule> findByTeam_idAndStartLessThanEqualAndEndGreaterThanEqual(String teamId, Date start, Date end);

    public List<TeamSchedule> findByTeam_idAndRole(String teamId, Integer role);
}
