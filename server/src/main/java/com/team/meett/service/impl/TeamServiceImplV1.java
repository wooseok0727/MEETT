package com.team.meett.service.impl;

import com.team.meett.model.Team;
import com.team.meett.repository.TeamRepository;
import com.team.meett.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
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
        log.debug(String.valueOf(team));
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
        if(teamRepository.existsById(team.getId())){
            teamRepository.save(team);
            //서버에서 처리 했으나 혹시 프론트에서도 필요한 값인지
        }

    }

    @Override
    public String delete(String teamId) {
        if(teamRepository.existsById(teamId)){
            teamRepository.deleteById(teamId);
            return "succeed";
        }
        return "fail";
    }
}
