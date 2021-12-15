package com.team.meett.service.impl;

import com.team.meett.dto.TeamRequestDto;
import com.team.meett.dto.TeamResponseDto;
import com.team.meett.model.Team;
import com.team.meett.repository.TeamRepository;
import com.team.meett.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service("TeamService")
@RequiredArgsConstructor
public class TeamServiceImplV1 implements TeamService {

    private final TeamRepository teamRepository;

    @Override
    public List<TeamResponseDto> selectAll() {
        return teamRepository.findAll().stream().map(TeamResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public Optional<TeamResponseDto> findById(String teamId) {
        return teamRepository.findById(teamId).map(TeamResponseDto::new);
    }

    @Override
    public List<TeamResponseDto> findByTitle(String title) {
        return teamRepository.findByTitle(title).stream().map(TeamResponseDto::new).collect(Collectors.toList());
    }


    @Override
    public void insert(TeamRequestDto team) {
        teamRepository.save(team.toEntity());
    }

    @Override
    public void update(TeamRequestDto team) {
        if (teamRepository.existsById(team.getTeamId())) {
            teamRepository.save(team.toEntity());
            //서버에서 처리 했으나 혹시 프론트에서도 필요한 값인지
        }

    }

    @Override
    public String delete(String teamId) {
        if (teamRepository.existsById(teamId)) {
            teamRepository.deleteById(teamId);
            return "succeed";
        }
        return "fail";
    }
}
