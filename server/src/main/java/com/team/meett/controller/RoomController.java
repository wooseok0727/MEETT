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
     * url 경로 이름 수정해야함
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
     * 팀 스케줄의 상세내용으로 검색
     * 검색할 팀의 Id 값과 검색어를 받아서 조회함
     * url 경로 수정해야함
     */
    @GetMapping("/test2")
    public ResponseEntity<?> searchSchedule(@RequestParam(value="teamId", required = false) String teamId,
                                            @RequestParam(value="detail", required = false) String detail){
        List<TeamSchedule> teamScheduleList;
        if(detail != null && teamId != null){
            if(!teamService.findById(teamId).isEmpty()){
                teamScheduleList = searchService.searchByTeam_idAndDetailContains(teamId, detail);
                if(teamScheduleList.isEmpty()){
                    return ResponseEntity.badRequest().body(teamId + "에 " + detail + "을 포함한 상세일정은 존재하지 않습니다");
                }
            } else {
                return ResponseEntity.badRequest().body(teamId + "는 존재하지 않는 팀");
            }
        } else {
            return ResponseEntity.badRequest().body("teamId 또는 detail이 없습니다");
        }
        return ResponseEntity.ok(teamScheduleList);
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

    /**
     * 지정한 날짜를 통해 현재 페이지의 팀의 일정을 조회하는 method
     * 날짜 하루를 조회하려면 start 와 end를 같은 값으로 보내줘야함 ex) start = 2021-12-23, end = 2021-12-23
     * url 경로 수정 필요
     */
    @GetMapping("/test4")
    public ResponseEntity<?> searchTeamDate(@RequestParam(value = "teamId", required = false)String teamId,
                                         @RequestParam(value = "start", required = false)String start,
                                         @RequestParam(value = "end", required = false) String end) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if((teamId != null)&&(start != null) && (end != null)){
            if(!teamService.findById(teamId).isEmpty()){
                Date dStart = formatter.parse(start);
                Date dEnd = formatter.parse(end);
                if(dStart.equals(dEnd) || dStart.before(dEnd)){
                    List<TeamSchedule> teamScheduleList = searchService.searchByTeamDate(teamId, dStart, dEnd);
                    /**
                     * DB의 Date Type과 Entity의 Date Type 을 일치 시켜주고 시간은 빼야한다
                     * 현재 이렇게 시간 값까지 도출된다 -> 2021-02-05 00:00:00.0
                     * 데이터는 잘 나옴
                     */
                    return ResponseEntity.status(200).body(teamScheduleList);
                } else {return ResponseEntity.badRequest().body("start 값이 end 값보다 큽니다 다시 확인하세요");}
            } else { return ResponseEntity.badRequest().body(teamId + "이 존재하지 않습니다");}
        }return ResponseEntity.badRequest().body("teamId, start, end 중 null 값이 있습니다 확인하세요");}
    /**
     * 지정한 날짜를 통해 현재 페이지의 유저의 일정을 조회하는 method
     * 날짜 하루를 조회하려면 start 와 end를 같은 값으로 보내줘야함 ex) start = 2021-12-23, end = 2021-12-23
     * url 경로 수정 필요
     */
    @GetMapping("/test5")
    public ResponseEntity<?> searchUserDate(@RequestParam(value = "username", required = false) String username,
                                         @RequestParam(value = "start", required = false) String start,
                                         @RequestParam(value = "end", required = false) String end) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if((username != null) && (start != null) && (end != null)){
            if(userService.findById(username) != null){
                Date dStart = formatter.parse(start);
                Date dEnd = formatter.parse(end);
                if(dStart.equals(dEnd) || dStart.before(dEnd)){
                    List<UserSchedule> userScheduleList = searchService.searchByUserDate(username, dStart, dEnd);
                    return ResponseEntity.status(200).body(userScheduleList);
                } else {return ResponseEntity.badRequest().body("start 값이 end 값보다 큽니다 다시 확인해주세요");}
            } else {return ResponseEntity.badRequest().body(username + "이 존재하지 않습니다");}
        } return ResponseEntity.badRequest().body("username, start, end 중 null 값이 있습니다 확인하세요");
    }

}
