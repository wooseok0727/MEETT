package com.team.meett.service.impl;

import com.team.meett.model.Team;
import com.team.meett.model.TeamSchedule;
import com.team.meett.repository.TeamRepository;
import com.team.meett.repository.TeamScheduleRepository;
import com.team.meett.service.SearchService;
import com.team.meett.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("SearchService")
@RequiredArgsConstructor
public class SearchServiceImplV1 implements SearchService {

    private final TeamRepository teamRepository;
    private final TeamScheduleRepository teamScheduleRepository;


    // Team의 Title 유사 검색
    @Override
    public List<Team> searchByContainTeamTitle(String title) {
        return teamRepository.findByTitleContains(title);
    }

    @Override
    public List<Team> searchByTeamTitle(String title) {

        return teamRepository.findByTitle(title);
    }

    @Override
    public List<TeamSchedule> searchByContainTeamScheduleTitle(String title) {
        return teamScheduleRepository.findByTitleContains(title);
    }

    @Override
    public List<TeamSchedule> searchByTeamScheduleTitle(String title) {
        return teamScheduleRepository.findByTitle(title);
    }

    @Override
    public List<TeamSchedule> searchByContainTeamScheduleDetail(String detail) {
        return teamScheduleRepository.findByDetailContains(detail);
    }
}
