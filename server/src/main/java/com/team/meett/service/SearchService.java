package com.team.meett.service;

import com.team.meett.model.Team;
import com.team.meett.model.TeamSchedule;

import java.util.List;

public interface SearchService {

    public List<Team> searchByContainTeamTitle(String title);
    public List<Team> searchByTeamTitle(String title);

    public List<TeamSchedule> searchByContainTeamScheduleTitle(String title);
    public List<TeamSchedule> searchByTeamScheduleTitle(String title);
    public List<TeamSchedule> searchByContainTeamScheduleDetail(String detail);
}
