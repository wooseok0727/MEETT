package com.team.meett.service;

import com.team.meett.dto.TsRequestDto;
import com.team.meett.dto.TsResponseDto;

import java.util.List;
import java.util.Optional;

public interface TeamScheduleService {

    public List<TsResponseDto> selectAll();

    public List<TsResponseDto> findByUsername(String username);
    public List<TsResponseDto> findByTeamId(String teamId);
    public Optional<TsResponseDto> findById(Long seq);

    public void insert(TsRequestDto teamSchedule);
    public void update(TsRequestDto teamSchedule, Long seq);
    public void delete(Long seq);

}
