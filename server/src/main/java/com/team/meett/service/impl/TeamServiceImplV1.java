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
    public Optional<Team> findById(String t_tameId) {
        // TODO: Optional UserScheduleServiceImplV1과 달리 설정해봄(수정필요)
        return teamRepository.findById(t_tameId);
    }

    @Override
    public List<Team> findByT_title(String t_title) {
        return null;
    }


    @Override
    public void insert(Team team) {

    }

    @Override
    public void update(Team team) {

    }

    @Override
    public void delete(String t_teamId) {

    }
}
