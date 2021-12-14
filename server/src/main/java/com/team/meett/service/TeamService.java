package com.team.meett.service;

import com.team.meett.model.Team;

import java.util.List;

public interface TeamService {
    public List<Team> selectAll();

    public Team findById(String teamId);
    public List<Team> findByTitle(String title);

    public void insert(Team team);
    public void update(Team team);
    public String delete(String teamId);
}