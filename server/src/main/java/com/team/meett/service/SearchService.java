package com.team.meett.service;

import com.team.meett.dto.SearchTeamResponseDto;
import com.team.meett.dto.TsResponseDto;
import com.team.meett.dto.UsResponseDto;
import com.team.meett.model.TeamSchedule;
import com.team.meett.model.UserSchedule;

import java.util.Date;
import java.util.List;

public interface SearchService {

    public List<SearchTeamResponseDto> searchByContainTeamTitle(String title);
    public List<SearchTeamResponseDto> searchByTeamTitle(String title);

    public List<TeamSchedule> searchByContainTeamScheduleTitle(String teamId, String title);
    public List<TeamSchedule> searchByTeamScheduleTitle(String teamId, String title);
    public List<TeamSchedule> searchByTeam_idAndDetailContains(String teamId, String details);

    public String searchByPassword(String teamId);

    public List<TeamSchedule> searchByTeamDate(String teamId, Date start, Date end);
    public List<UserSchedule> searchByUserDate(String username, Date start, Date end);

    public List<UsResponseDto> searchByUserScheduleRole(String username, Integer role);
    public List<TsResponseDto> searchByTeamScheduleRole(String teamId, Integer role);
}
