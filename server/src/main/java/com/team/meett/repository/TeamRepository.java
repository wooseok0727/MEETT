package com.team.meett.repository;

import com.team.meett.model.Team;
import com.team.meett.model.TeamSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TeamRepository extends JpaRepository<Team, String> {

    public List<Team> findByTitle(String title);

    public List<Team> findByTitleContains(String title);

    public Team findByPassword(String teamId);


}

