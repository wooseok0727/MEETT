package com.team.meett.controller;

import com.team.meett.dto.TeamRequestDto;
import com.team.meett.dto.TeamResponseDto;
import com.team.meett.service.SearchService;
import com.team.meett.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TeamController {

    protected final TeamService teamService;
    protected final SearchService searchService;

    //    find by title :: 팀명으로 조회했을 경우
    @GetMapping("/team")
    public ResponseEntity<?> selectTitle(@RequestParam(value = "title", required = false) String title) {
        log.debug("실행");
        List<TeamResponseDto> teamList;
        if (title != null) {
            // findByTitle -> searchByTitle 로 변경
            teamList = teamService.findByTitle(title);
            if (teamList.isEmpty()) {
                return ResponseEntity.badRequest().body(title + "은 존재하지 않는 모임입니다");
            }
        } else {
            teamList = teamService.selectAll();
            if (teamList.isEmpty()) {
                return ResponseEntity.badRequest().body("Team이 존재하지 않습니다");
            }
        }
        return ResponseEntity.ok().body(teamList);
    }

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
