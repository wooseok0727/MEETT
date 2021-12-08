package com.team.meett.service;

import com.team.meett.model.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    public List<Team> selectAll();

    public Team findById(String teamId);
    public List<Team> findByTitle(String title);

    public void insert(Team team);
    public void update(Team team);
    public void delete(String teamId);
}