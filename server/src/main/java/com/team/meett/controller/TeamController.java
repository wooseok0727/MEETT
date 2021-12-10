package com.team.meett.controller;

import com.team.meett.model.Team;
import com.team.meett.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestControllerAdvice
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class TeamController {

//    @ExceptionHandler(Exception e)
//    public String ExceptionHandler(Exception e){
//        return null;
//    }


    protected final TeamService teamService;

    //    find by title :: 팀명으로 조회했을 경우
    @GetMapping("/team")
    public ResponseEntity<?> selectTitle(@RequestParam(value = "title", required = false) String title) {
        List<Team> teamList;
        if (title != null) {
            teamList = teamService.findByTitle(title);
            if (teamList.isEmpty()) {
                return ResponseEntity.ok().body(title + "은 존재하지 않는 모임입니다");
            }
        } else {
            teamList = teamService.selectAll();
            if (teamList.isEmpty()) {
                return ResponseEntity.ok().body("Team이 존재하지 않습니다");
            }
        }
        return ResponseEntity.ok(teamList);
    }

    // get insert post insert
    @GetMapping("/team/create")
    public ResponseEntity<?> teamCreate(){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/team/create")
    public ResponseEntity<?> teamCreate(@RequestBody Team team){
        teamService.insert(team);
        return ResponseEntity.ok().body(team);
    }

    //update
    @PutMapping("/team/{teamId}")
    public ResponseEntity<?> update(@PathVariable String teamId){
        Team team = teamService.findById(teamId);
        teamService.update(team);
        return ResponseEntity.ok().body(team);
    }

    @DeleteMapping("/user/{teamId}")
    public ResponseEntity<?> delete(@PathVariable String teamId){
        teamService.delete(teamId);
        return ResponseEntity.ok().body("삭제완료");
    }

}
