package com.team.meett.repository;

import com.team.meett.model.UserSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface UserScheduleRepository extends JpaRepository<UserSchedule, Long> {

    public List<UserSchedule> findByUsername(String username);

    public List<UserSchedule> findByTitle(String title);

    public List<UserSchedule> findByUsernameAndStartLessThanEqualAndEndGreaterThanEqual(String username, Date start, Date end);

    public List<UserSchedule> findByUsernameAndRole(String username, int role);

}
