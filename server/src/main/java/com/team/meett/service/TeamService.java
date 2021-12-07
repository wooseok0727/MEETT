package com.team.meett.service;

import com.team.meett.model.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    public List<Team> selectAll();

    public Optional<Team> findById(String t_tameId);
    public List<Team> findByT_title(String t_title);

    public void insert(Team team);
    public void update(Team team);
    public void delete(String t_teamId);
}