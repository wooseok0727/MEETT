package com.team.meett.controller;

import com.team.meett.dto.SearchTeamResponseDto;
import com.team.meett.model.Room;
import com.team.meett.model.Team;
import com.team.meett.model.TeamSchedule;
import com.team.meett.model.UserSchedule;
import com.team.meett.service.RoomService;
import com.team.meett.service.SearchService;
import com.team.meett.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class RoomController {

    protected final RoomService roomService;
    protected final SearchService searchService;
    protected final TeamService teamService;

    @GetMapping("/{username}")
    public ResponseEntity selectTeam(@PathVariable String username){
        List<Room> roomList = roomService.findByUsername(username);

        if(roomList.isEmpty()){
            return ResponseEntity.ok().body("데이터 없음");
        }
        return  ResponseEntity.ok(roomList);
    }

    @GetMapping("/teamId/{teamId}")
    public ResponseEntity selectUser(@PathVariable String teamId){
        List<Room> roomList = roomService.findByTeamId(teamId);
        if(roomList.isEmpty()){
            return ResponseEntity.ok().body("데이터 없음");
        }
        return ResponseEntity.ok(roomList);
    }

    @DeleteMapping("/exit/{username}/{teamId}")
    public ResponseEntity delete(@PathVariable String username, @PathVariable String teamId){
        Room room = roomService.findByUsernameAndTeamId(username, teamId);

        if(room.getId() == null){
            return ResponseEntity.status(200).body("데이터 없음");
        }
        roomService.delete(room.getId());
        return ResponseEntity.status(200).body("삭제완료");
    }

    @PostMapping("/join")
    public ResponseEntity joinRoom(@RequestBody Room room){
        roomService.insert(room);
        return ResponseEntity.status(200).body(room);
    }


    @GetMapping("/test")
    public ResponseEntity searchTeam(@RequestParam(value = "title", required = false) String title){

        List<SearchTeamResponseDto> teamList;
        if(title != null){
            teamList = searchService.searchByContainTeamTitle(title);
            teamList.addAll(0,searchService.searchByTeamTitle(title));

            if(teamList.isEmpty()){
                return ResponseEntity.ok().body(title + "은 존재하지 않는 모임");
            }
        } else {
            return ResponseEntity.ok().body("검색어를 입력해주세요");
        }
        return ResponseEntity.ok(teamList);
    }

    @GetMapping("/test2")
    public ResponseEntity searchSchedule(@RequestParam(value = "teamId", required = false) String teamId, @RequestParam(value="detail", required = false) String detail){
        List<TeamSchedule> teamScheduleList;
        if(teamId != null){
            teamScheduleList = searchService.searchByTeam_idAndDetailContains(teamId, detail);
            if(teamScheduleList.isEmpty()){
                return ResponseEntity.ok().body(detail + "은 존재하지 않는 스케줄명");
            }
        } else {
            return ResponseEntity.ok().body("검색어를 입력해주세요");
        }
        return ResponseEntity.ok(teamScheduleList);
    }

    @GetMapping("/test3")
    public ResponseEntity searchPassword(@RequestParam(value = "teamId", required = false) String teamId, @RequestParam(value = "password", required = false) String password){

        //log.debug(teamService.findById(teamId).get().getPassword());
        //log.debug(password);
        if(teamService.findById(teamId).get().getPassword().equals(password)){
            return ResponseEntity.status(200).body("비밀번호 일치");
        }
        return ResponseEntity.status(200).body("비밀번호 불일치");
    }

    @GetMapping("/test4")
    public ResponseEntity searchTeamDate(@RequestParam(value = "start", required = false)String start, @RequestParam(value = "end", required = false) String end) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date dStart = formatter.parse(start);
        Date dEnd = formatter.parse(end);

        List<TeamSchedule> teamScheduleList = searchService.searchByTeamDate(dStart, dEnd);
        /**
         * DB의 Date Type과 Entity의 Date Type 을 일치 시켜주고 시간은 빼야한다
         * 현재 이렇게 시간 값까지 도출된다 -> 2021-02-05 00:00:00.0
         * 데이터는 잘 나옴
         */
//        log.debug(String.valueOf(teamScheduleList.get(0).getStart()));
//        log.debug(String.valueOf(teamScheduleList.get(0).getEnd()));
        return ResponseEntity.status(200).body(teamScheduleList);
    }

    @GetMapping("/test5")
    public ResponseEntity searchUserDate(@RequestParam(value = "start", required = false) String start, @RequestParam(value = "end", required = false) String end) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dStart = formatter.parse(start);
        Date dEnd = formatter.parse(end);

        List<UserSchedule> teamList = searchService.searchByUserDate(dStart, dEnd);
        return ResponseEntity.status(200).body(teamList);
    }

}
