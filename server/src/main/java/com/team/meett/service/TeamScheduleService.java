package com.team.meett.service;

import com.team.meett.model.TeamSchedule;

import java.util.List;

public interface TeamScheduleService {

    public List<TeamSchedule> selectAll();

    public List<TeamSchedule> findByUsername(String username);
    public List<TeamSchedule> findByTeamId(String teamId);
    public TeamSchedule findBySeq(Long seq);

    public void insert(TeamSchedule teamSchedule);
    public void update(TeamSchedule teamSchedule, Long seq);
    public void delete(Long seq);

}
