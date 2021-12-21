package com.team.meett.service;

import com.team.meett.dto.UsRequestDto;
import com.team.meett.dto.UsResponseDto;
import com.team.meett.model.UserSchedule;

import java.util.List;
import java.util.Optional;

public interface UserScheduleService {
    public List<UsResponseDto> selectAll();

    public Optional<UsResponseDto> findById(Long seq);
    public List<UsResponseDto> findByUsername(String username);
    public List<UsResponseDto> findByTitle(String title);

    public void insert(UsRequestDto userSchedule);
    public void update(UsRequestDto userSchedule, Long usSeq);
    public void delete(Long seq);
}
