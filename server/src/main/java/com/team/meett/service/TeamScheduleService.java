package com.team.meett.service;

import com.team.meett.model.TeamSchedule;

import java.util.List;

public interface TeamScheduleService {

    public List<TeamSchedule> selectAll();

    public List<TeamSchedule> findByUsername(String username);

    public void insert(TeamSchedule teamSchedule);
    public void update(TeamSchedule teamSchedule);
    public void delete(TeamSchedule teamSchedule);

}
