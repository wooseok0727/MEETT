package com.team.meett.service.impl;

import com.team.meett.dto.TeamRequestDto;
import com.team.meett.dto.TeamResponseDto;
import com.team.meett.repository.TeamRepository;
import com.team.meett.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service("TeamService")
@RequiredArgsConstructor
public class TeamServiceImplV1 implements TeamService {

    private final TeamRepository teamRepository;
    private final PasswordEncoder passwordEncoder;

    // 전체 Team List 조회
    @Override
    public List<TeamResponseDto> selectAll() {
        return teamRepository.findAll().stream().map(TeamResponseDto::new).collect(Collectors.toList());
    }

    // 단일 Team 정보 조회
    @Override
    public Optional<TeamResponseDto> findById(String teamId) {
        return teamRepository.findById(teamId).map(TeamResponseDto::new);
    }

    // 유효성 검사
    @Override
    public List<TeamResponseDto> findByTitle(String title) {
        return teamRepository.findByTitle(title).stream().map(TeamResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public void insert(TeamRequestDto team) {
        //암호화코드
        String encodePassword = passwordEncoder.encode(team.getPassword());
        team.setPassword(encodePassword);

        teamRepository.save(team.toEntity());
    }

    @Override
    public void update(TeamRequestDto team, String id) {
        if (teamRepository.existsById(id)) {
            insert(team);
        } else throw new EmptyResultDataAccessException(1);
    }

    // return 값 논의 필요
    @Override
    public void delete(String teamId) {
        if (teamRepository.existsById(teamId)) {

            teamRepository.deleteById(teamId);
        } else throw new EmptyResultDataAccessException(1);
    }
}
