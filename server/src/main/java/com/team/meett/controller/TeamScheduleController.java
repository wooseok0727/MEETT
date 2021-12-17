package com.team.meett.controller;

import com.team.meett.model.TeamSchedule;
import com.team.meett.service.TeamScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class TeamScheduleController {

    protected final TeamScheduleService teamScheduleService;

    // team schedule 전체 조회
    @GetMapping("/team/{teamId}")
    public ResponseEntity<?> selectUsername(@PathVariable String teamId){
        List<TeamSchedule> tsList = teamScheduleService.findByTeamId(teamId);

        if(tsList.isEmpty()){
            return ResponseEntity.ok().body("teamschedule이 존재하지 않습니다");
        }
        return ResponseEntity.ok(tsList);
    }

    // 팀방 스케줄 등록
    @PostMapping("/team")
    public ResponseEntity<?> insert(@RequestBody TeamSchedule teamSchedule){
        teamScheduleService.insert(teamSchedule);
        return ResponseEntity.status(200).body(teamSchedule);
    }

    // 팀방 스케줄 수정
    @PutMapping("/team/{seq}")
    public TeamSchedule update(@RequestBody TeamSchedule updateTeamSchedule, @PathVariable Long seq){
        teamScheduleService.update(updateTeamSchedule,seq);
        return updateTeamSchedule; //ResponseEntity.status(200).body(teamScheduleService.selectAll());
    }

    @DeleteMapping("/team/{seq}")
    public ResponseEntity<?> delete(@PathVariable Long seq){
        teamScheduleService.delete(seq);
        return ResponseEntity.status(200).body("삭제완료");
    }

    //예외처리


}
