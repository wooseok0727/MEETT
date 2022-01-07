package com.team.meett.controller;

import com.team.meett.dto.*;
import com.team.meett.model.Room;
import com.team.meett.model.Team;
import com.team.meett.model.TeamSchedule;
import com.team.meett.repository.TeamRepository;
import com.team.meett.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    protected final SearchService searchService;
    protected final UserService userService;


    // team schedule 전체 조회
    @GetMapping("/team/{teamId}")
    public ResponseEntity<?> selectUsername(@PathVariable String teamId){
        List<TsResponseDto> tsList = teamScheduleService.findByTeamId(teamId);

        if(tsList.isEmpty()){
            //잘못된 리퀘스트가 아닌 아직 정해진 스케줄이 없는 모임일 경우
            return ResponseEntity.ok().body("teamschedule이 존재하지 않습니다");
        }
        return ResponseEntity.ok(tsList);
    }
    /**
     * 팀 스케줄의 상세내용으로 검색
     * 검색할 팀의 Id 값과 검색어를 받아서 조회함
     */
    @GetMapping("/team/search/detail")
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
     * 지정한 날짜를 통해 현재 페이지의 팀의 일정을 조회하는 method
     * 날짜 하루를 조회하려면 start 와 end를 같은 값으로 보내줘야함 ex) start = 2021-12-23, end = 2021-12-23
     */
    @GetMapping("/team/search/date")
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
     * TeamSchedule 에서 Role 에 맞는 일정 조회
     */
    @GetMapping("/team/search/role")
    public ResponseEntity<?> searchTeamScheduleRole(@RequestParam(value = "teamId", required = false) String teamId,
                                                    @RequestParam(value = "role", required = false)Integer role){
        if((teamId != null) && (role != null)){
            if(teamService.findById(teamId) != null){
                if(role == 0 || role == 1){
                    return ResponseEntity.status(200).body(searchService.searchByTeamScheduleRole(teamId, role));
                } else {
                    return ResponseEntity.badRequest().body("role 값이 옳지 않습니다");
                }
            } else {
                return ResponseEntity.badRequest().body(teamId + "해당 팀이 존재하지 않습니다");
            }
        } return ResponseEntity.badRequest().body("teamId 또는 role 값이 null 입니다");
    }

    // user schedule -> teamSchedule
    @PostMapping("/team/{userSeq}/{teamId}")
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

    @DeleteMapping("/team/{username}/{seq}")
    public ResponseEntity<?> delete(@PathVariable String username, @PathVariable Long seq){
        if(((userService.findById(username).getUsername()) != null) && seq != null){
            if(!teamScheduleService.findById(seq).isEmpty()){
                if(teamScheduleService.findById(seq).get().getUsername().equals(username)){
                    teamScheduleService.delete(seq);
                    return ResponseEntity.status(200).body("삭제완료");
                } else {
                    return ResponseEntity.badRequest().body(username + " 해당 유저는 팀장이 아닙니다. 팀 일정은 팀장만 삭제 할 수 있습니다");
                }
            } else {
                return ResponseEntity.badRequest().body(seq + " 해당 seq 값은 존재하지 않습니다");
            }
        } else{
            return ResponseEntity.badRequest().body("username 또는 seq 값이 null");
        }
    }




}
