package com.team.meett.controller;

import com.team.meett.model.Room;
import com.team.meett.model.Team;
import com.team.meett.service.RoomService;
import com.team.meett.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class RoomController {

    protected final RoomService roomService;
    protected final SearchService searchService;

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

//    @GetMapping("/test")
//    public ResponseEntity searchTeam(@RequestParam(value = "title", required = false)String title){
//
//        List<Team> teamList;
//        if(title != null){
//            teamList = searchService.searchByTitle(title);
//            if(teamList.isEmpty()){
//                return ResponseEntity.ok().body(title + "은 존재하지 않는 모임");
//            }
//        } else {
//            return ResponseEntity.ok().body("검색어를 입력해주세요");
//        }
//        return ResponseEntity.ok(teamList);
//    }

}
