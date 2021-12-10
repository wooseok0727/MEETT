package com.team.meett.repository;

import com.team.meett.model.UserSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserScheduleRepository extends JpaRepository<UserSchedule, Long> {

    public List<UserSchedule> findByUsername(String username);

    public List<UserSchedule> findByTitle(String title);
}
