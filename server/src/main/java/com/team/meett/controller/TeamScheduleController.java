package com.team.meett.controller;

import com.team.meett.dto.*;
import com.team.meett.model.Room;
import com.team.meett.model.Team;
import com.team.meett.model.TeamSchedule;
import com.team.meett.repository.TeamRepository;
import com.team.meett.service.RoomService;
import com.team.meett.service.TeamScheduleService;
import com.team.meett.service.TeamService;
import com.team.meett.service.UserScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class TeamScheduleController {

    protected final TeamScheduleService teamScheduleService;
    protected final UserScheduleService userScheduleService;
    protected final RoomService roomService;
    protected final TeamService teamService;
    protected final TeamRepository teamRepository;


    // team schedule 전체 조회
    @GetMapping("/team/{teamId}")
    public ResponseEntity<?> selectUsername(@PathVariable String teamId){
        List<TsResponseDto> tsList = teamScheduleService.findByTeamId(teamId);

        if(tsList.isEmpty()){
            return ResponseEntity.ok().body("teamschedule이 존재하지 않습니다");
        }
        return ResponseEntity.ok(tsList);
    }

    // user schedule -> teamSchedule
    @GetMapping("/team/{userSeq}/{teamId}")
    public ResponseEntity<?> userScheduleInsert(@PathVariable Long userSeq, @PathVariable String teamId){
        Optional<UsResponseDto> userSchedule = userScheduleService.findById(userSeq);

        TsRequestDto tsRequestDto = new TsRequestDto();
        tsRequestDto.setUsername(userSchedule.get().getUsername());
        tsRequestDto.setTitle(userSchedule.get().getTitle());
        tsRequestDto.setDetail(userSchedule.get().getDetail());
        tsRequestDto.setStart(userSchedule.get().getStart());
        tsRequestDto.setEnd(userSchedule.get().getEnd());
        tsRequestDto.setTeamId(teamId);

        teamScheduleService.insert(tsRequestDto);
        return ResponseEntity.status(200).body(tsRequestDto);
    }

    // 팀방 스케줄 등록
    @PostMapping("/team")
    public ResponseEntity<?> insert(@RequestBody TsRequestDto teamSchedule){

        UsRequestDto usRequestDto = new UsRequestDto();

        int i = 0;
        teamScheduleService.insert(teamSchedule);
        log.debug(teamSchedule.getTeamId());
        List<Room> roomList = roomService.findByTeamId(teamSchedule.getTeamId());
        for(i = 0; i < roomList.size(); i++){
            usRequestDto.setUsername(roomList.get(i).getUsername());
            usRequestDto.setTitle(teamSchedule.getTitle());
            usRequestDto.setDetail(teamSchedule.getDetail());
            usRequestDto.setStart(teamSchedule.getStart());
            usRequestDto.setEnd(teamSchedule.getEnd());
            usRequestDto.setRole(teamSchedule.getRole());
            userScheduleService.insert(usRequestDto);
            log.debug(i + " 번째" + roomList.get(i).getUsername() + "insert 완료");
        }

        return ResponseEntity.status(200).body("완료");

    }


    // 팀방 스케줄 수정
    @PutMapping("/team/{seq}")
    public TsRequestDto update(@RequestBody TsRequestDto updateTeamSchedule, @PathVariable Long seq){
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
