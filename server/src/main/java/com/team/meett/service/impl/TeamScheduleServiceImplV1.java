package com.team.meett.service.impl;

import com.team.meett.dto.TsRequestDto;
import com.team.meett.dto.TsResponseDto;
import com.team.meett.repository.TeamScheduleRepository;
import com.team.meett.service.TeamScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("TeamScheduleService")
@RequiredArgsConstructor
public class TeamScheduleServiceImplV1 implements TeamScheduleService {

    private final TeamScheduleRepository teamScheduleRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<TsResponseDto> selectAll() {

        return teamScheduleRepository.findAll().stream().map(TsResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public List<TsResponseDto> findByUsername(String username) {

        return teamScheduleRepository.findByUsername(username).stream().map(TsResponseDto::new).collect(Collectors.toList());

    }

    @Override
    public List<TsResponseDto> findByTeamId(String teamId) {
        return teamScheduleRepository.findByTeam_id(teamId).stream().map(TsResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public Optional<TsResponseDto> findById(Long seq) {
        return teamScheduleRepository.findById(seq).map(TsResponseDto::new);
    }


    @Override
    public void insert(TsRequestDto teamSchedule) {
        teamScheduleRepository.save(teamSchedule.toEntity());

    }

    // return 값 논의 필요
    @Override
    public void update(TsRequestDto teamSchedule, Long seq) {
        if (teamScheduleRepository.existsById(seq)) {
            teamScheduleRepository.save(teamSchedule.toEntity());
        }
    }

    // return 값 논의 필요
    @Override
    public void delete(Long seq) {
        if(teamScheduleRepository.existsById(seq)){
            teamScheduleRepository.deleteById(seq);
        }
    }
}
