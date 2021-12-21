package com.team.meett.service.impl;

import com.team.meett.dto.SearchTeamResponseDto;
import com.team.meett.dto.TeamResponseDto;
import com.team.meett.model.Team;
import com.team.meett.model.TeamSchedule;
import com.team.meett.model.UserSchedule;
import com.team.meett.repository.TeamRepository;
import com.team.meett.repository.TeamScheduleRepository;
import com.team.meett.repository.UserScheduleRepository;
import com.team.meett.service.SearchService;
import com.team.meett.service.UserScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("SearchService")
@RequiredArgsConstructor
public class SearchServiceImplV1 implements SearchService {

    private final TeamRepository teamRepository;
    private final TeamScheduleRepository teamScheduleRepository;
    private final UserScheduleRepository userScheduleRepository;


    // Team의 Title 유사 검색
    @Override
    public List<SearchTeamResponseDto> searchByContainTeamTitle(String title) {

        return teamRepository.findByTitleContains(title).stream().map(SearchTeamResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public List<SearchTeamResponseDto> searchByTeamTitle(String title) {

        return teamRepository.findByTitle(title).stream().map(SearchTeamResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public List<TeamSchedule> searchByContainTeamScheduleTitle(String teamId, String title) {
        return teamScheduleRepository.findByTeam_idAndTitleContains(teamId, title);
    }

    @Override
    public List<TeamSchedule> searchByTeamScheduleTitle(String teamId, String title) {
        return teamScheduleRepository.findByTeam_idAndTitle(teamId, title);
    }

    @Override
    public List<TeamSchedule> searchByTeam_idAndDetailContains(String teamId, String details) {
        return teamScheduleRepository.findByTeam_idAndDetailContains(teamId, details);
    }

    @Override
    public String searchByPassword(String teamId) {

        return teamRepository.findByPassword(teamId).getPassword();
    }

    @Override
    public List<TeamSchedule> searchByTeamDate(String teamId, Date start, Date end) {

        return teamScheduleRepository.findByTeam_idAndStartLessThanEqualAndEndGreaterThanEqual(teamId, start, end);
    }

    @Override
    public List<UserSchedule> searchByUserDate(String username, Date start, Date end) {
        return userScheduleRepository.findByUsernameAndStartLessThanEqualAndEndGreaterThanEqual(username, start, end);
    }
}
