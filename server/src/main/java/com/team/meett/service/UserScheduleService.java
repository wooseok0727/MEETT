package com.team.meett.service;

import com.team.meett.model.UserSchedule;

import java.util.List;

public interface UserScheduleService {
    public List<UserSchedule> selectAll();

    public UserSchedule findById(Long seq);
    public List<UserSchedule> findByUsername(String username);
    public List<UserSchedule> findByTitle(String title);

    public void insert(UserSchedule userSchedule);
    public void update(UserSchedule userSchedule);
    public void delete(Long seq);
}
