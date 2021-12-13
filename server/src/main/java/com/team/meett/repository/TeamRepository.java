package com.team.meett.repository;

import com.team.meett.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, String> {

    public List<Team> findByTitle(String title);
    // public List<Team> findByTitleAndUsername(String title, String name);
    // public List<Team> findByTitleAndStartAndEnd(String title, String name);
}