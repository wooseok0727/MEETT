package com.team.meett.controller;

import com.team.meett.model.TeamSchedule;
import com.team.meett.service.TeamScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tschedule")
@RequiredArgsConstructor
public class TeamScheduleController {

    protected final TeamScheduleService teamScheduleService;

    @GetMapping("/team/{username}")
    public ResponseEntity<?> selectUsername(@PathVariable String username){
        List<TeamSchedule> tsList = teamScheduleService.findByUsername(username);

        if(tsList.isEmpty()){
            return ResponseEntity.ok().body("teamschedule의 username 존재하지 않습니다");
        }
        return ResponseEntity.ok(tsList);
    }





}
