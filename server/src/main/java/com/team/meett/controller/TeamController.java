package com.team.meett.controller;

import com.team.meett.dto.TeamRequestDto;
import com.team.meett.dto.TeamResponseDto;
import com.team.meett.service.SearchService;
import com.team.meett.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
public class TeamController {

    protected final TeamService teamService;
    protected final SearchService searchService;

    @PostMapping("/team/create")
    public TeamRequestDto teamCreate(@RequestBody TeamRequestDto team) {
        teamService.insert(team);
        return team; //ResponseEntity.ok().body(team);
    }

    //update
    @PutMapping(value = "/team/{TeamId}")
    public TeamRequestDto update(@RequestBody TeamRequestDto updateTeam, @PathVariable String TeamId) {
        teamService.update(updateTeam, TeamId);
        return updateTeam;
    }

    @DeleteMapping("/team/{teamId}")
    public ResponseEntity<?> delete(@PathVariable String teamId) {
        teamService.delete(teamId);
        return ResponseEntity.ok().body("삭제 성공");
    }

}
