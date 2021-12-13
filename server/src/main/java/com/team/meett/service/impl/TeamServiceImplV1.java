package com.team.meett.service.impl;

import com.team.meett.model.Team;
import com.team.meett.repository.TeamRepository;
import com.team.meett.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("TeamService")
@RequiredArgsConstructor
public class TeamServiceImplV1 implements TeamService {

    private final TeamRepository teamRepository;

    @Override
    public List<Team> selectAll() {
        return teamRepository.findAll();
    }

    @Override
    public Team findById(String teamId) {
        Optional<Team> team = teamRepository.findById(teamId);
        return team.orElse(null);
    }

    @Override
    public List<Team> findByTitle(String title) {
        return teamRepository.findByTitle(title);
    }


    @Override
    public void insert(Team team) {
        teamRepository.save(team);
    }

    @Override
    public void update(Team team) {
        teamRepository.save(team);
    }

    @Override
    public void delete(String teamId) {
//        if(teamRepository.existsById(teamId)){
            teamRepository.deleteById(teamId);
//        }
    }
}
