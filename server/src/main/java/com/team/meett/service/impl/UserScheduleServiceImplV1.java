package com.team.meett.service.impl;

import com.team.meett.model.UserSchedule;
import com.team.meett.repository.UserScheduleRepository;
import com.team.meett.service.UserScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service("UsService")
@RequiredArgsConstructor
public class UserScheduleServiceImplV1 implements UserScheduleService {

    @Autowired
    private final UserScheduleRepository UsRepository;

    @Override
    public List<UserSchedule> selectAll() {
        return UsRepository.findAll();
    }

    @Override
    public UserSchedule findById(Long seq) {
        Optional<UserSchedule> userSchedule =  UsRepository.findById(seq);
        return userSchedule.orElse(null);
    }

    @Override
    public List<UserSchedule> findByUsername(String username) {
        return UsRepository.findByUsername(username);
    }

    @Override
    public List<UserSchedule> findByTitle(String title) {
        return null;
    }

    @Override
    public void insert(UserSchedule userSchedule) {
        UsRepository.save(userSchedule);
    }

    @Override
    public void update(UserSchedule userSchedule) {
        UsRepository.save(userSchedule);
    }

    @Override
    public void delete(Long seq) {
        UsRepository.deleteById(seq);
    }
}
