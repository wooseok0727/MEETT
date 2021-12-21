package com.team.meett.service;

import com.team.meett.dto.TeamRequestDto;
import com.team.meett.dto.TeamResponseDto;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    public List<TeamResponseDto> selectAll();

    public Optional<TeamResponseDto> findById(String teamId);
    public List<TeamResponseDto> findByTitle(String title);

    public void insert(TeamRequestDto team);
    public void update(TeamRequestDto team, String id);
    public void delete(String teamId);
}