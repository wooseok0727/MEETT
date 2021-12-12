package com.team.meett.service.impl;

import com.team.meett.model.TeamSchedule;
import com.team.meett.repository.TeamScheduleRepository;
import com.team.meett.service.TeamScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service("TeamScheduleService")
@RequiredArgsConstructor
public class TeamScheduleServiceImplV1 implements TeamScheduleService {

    private final TeamScheduleRepository teamScheduleRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<TeamSchedule> selectAll() {

        return teamScheduleRepository.findAll();
    }

    @Override
    public List<TeamSchedule> findByUsername(String username) {

        return teamScheduleRepository.findByUsername(username);

    }



    @Override
    public void insert(TeamSchedule teamSchedule) {

    }

    @Override
    public void update(TeamSchedule teamSchedule) {

    }

    @Override
    public void delete(TeamSchedule teamSchedule) {

    }
}
