package com.team.meett.controller;

import com.team.meett.dto.SearchTeamResponseDto;
import com.team.meett.dto.TeamResponseDto;
import com.team.meett.model.Room;
import com.team.meett.model.TeamSchedule;
import com.team.meett.model.UserSchedule;
import com.team.meett.service.RoomService;
import com.team.meett.service.SearchService;
import com.team.meett.service.TeamService;
import com.team.meett.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class RoomController {

    protected final RoomService roomService;
    protected final SearchService searchService;
    protected final TeamService teamService;
    protected final UserService userService;

    protected final PasswordEncoder passwordEncoder;

    @GetMapping("/{username}")
    public ResponseEntity<?> selectTeam(@PathVariable String username){
        List<Room> roomList = roomService.findByUsername(username);

        if(roomList.isEmpty()){
            return ResponseEntity.ok().body("데이터 없음");
        }
        return  ResponseEntity.ok(roomList);
    }

    @GetMapping("/teamId/{teamId}")
    public ResponseEntity<?> selectUser(@PathVariable String teamId){
        List<Room> roomList = roomService.findByTeamId(teamId);
        if(roomList.isEmpty()){
            return ResponseEntity.ok().body("데이터 없음");
        }
        return ResponseEntity.ok(roomList);
    }

    @DeleteMapping("/exit/{username}/{teamId}")
    public ResponseEntity<?> delete(@PathVariable String username, @PathVariable String teamId){
        Room room = roomService.findByUsernameAndTeamId(username, teamId);

        if(room.getId() == null){
            return ResponseEntity.status(200).body("데이터 없음");
        }
        roomService.delete(room.getId());
        return ResponseEntity.status(200).body("삭제완료");
    }

    @PostMapping("/join")
    public ResponseEntity<?> joinRoom(@RequestBody Room room){
        roomService.insert(room);
        return ResponseEntity.status(200).body(room);
    }


    /**
     * 방 제목(title)로 검색하는 method
     * 검색어와 정확히 일치하는 title이 있을때는
     * list 의 index 0값과 1값이 동일함
     *      (검색어와 정확히 일치하는 값 = index 0과 1, 포함하고 있는 값 2 ~ list.size() 까지)
     * 검색어와 정확히 일치하는 title이 없을 경우
     * 검색어를 포함하고 있는 결과값들이 출력됨
     *
     * url 경로 이름 수정해야함 (RoomController)
     */
    @GetMapping("/search/team")
    public ResponseEntity<?> searchTeam(@RequestParam(value="title", required = false) String title){

        List<SearchTeamResponseDto> teamList;
        if(title != null){
            teamList = searchService.searchByContainTeamTitle(title);
            teamList.addAll(0,searchService.searchByTeamTitle(title));

            if(teamList.isEmpty()){
                return ResponseEntity.badRequest().body(title + "은 존재하지 않는 모임");
            }
        } else {
            return ResponseEntity.badRequest().body("검색어를 입력해주세요");
        }
        return ResponseEntity.ok(teamList);
    }
    /**
     * 새로운 팀 입장시 비밀번호 확인 method
     * url 경로 수정 필요
     */
    @PostMapping("/test3")
    public ResponseEntity<?> searchPassword(@RequestParam(value = "teamId", required = false) String teamId,
                                            @RequestParam(value = "password", required = false) String password){
        //log.debug(teamService.findById(teamId).get().getPassword());
        //log.debug(password);
        // 암호화된 비밀번호 복호화 확인
        TeamResponseDto responseDto = teamService.findById(teamId).orElseThrow(()->new IllegalArgumentException(teamId));
        if(!passwordEncoder.matches(password, responseDto.getPassword())){
            return ResponseEntity.badRequest().body("비밀번호 불일치");
        }
        return ResponseEntity.ok().body("비밀번호 일치");
    }
}
