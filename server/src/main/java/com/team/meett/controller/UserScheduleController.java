package com.team.meett.controller;

import com.team.meett.dto.UsRequestDto;
import com.team.meett.dto.UsResponseDto;
import com.team.meett.model.UserSchedule;
import com.team.meett.service.SearchService;
import com.team.meett.service.UserScheduleService;
import com.team.meett.service.UserService;
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
@RequestMapping("/schedule")
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:3000")
public class UserScheduleController {

    protected final UserScheduleService UsService;
    protected final UserService userService;
    protected final SearchService searchService;

    //    select findByUsername
    @GetMapping("/user/{username}")
    public ResponseEntity<?> selectUsername(@PathVariable String username) {

        List<UsResponseDto> UsList = UsService.findByUsername(username);
        log.debug(UsList.toString());
        if (UsList.isEmpty()) {
            return ResponseEntity.ok().body("데이터 없음");
        }
        return ResponseEntity.ok(UsList);
    }
    /**
     * 지정한 날짜를 통해 현재 페이지의 유저의 일정을 조회하는 method
     * 날짜 하루를 조회하려면 start 와 end를 같은 값으로 보내줘야함 ex) start = 2021-12-23, end = 2021-12-23
     */
    @GetMapping("/user/search/date")
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

    /**
     * UserSchedule에서 Role 에 맞는 일정 조회
     */
    @GetMapping("/user/search/role")
    public ResponseEntity<?> searchUserScheduleRole(@RequestParam(value = "username", required = false) String username,
                                                    @RequestParam(value = "role", required = false) Integer role){
        if((username != null) && (role != null)){
            if(userService.findById(username) != null){
                if(role == 0 || role == 1){
                    return ResponseEntity.status(200).body(searchService.searchByUserScheduleRole(username, role));
                } else {
                    return ResponseEntity.badRequest().body("role 값이 옳지 않습니다");
                }
            } else {
                return ResponseEntity.badRequest().body(username + "해당 user 가 존재하지 않습니다");
            }
        } return ResponseEntity.badRequest().body("username 또는 role 값이 null 입니다");
    }

    @PostMapping("/user")
    public ResponseEntity<?> insert(@RequestBody UsRequestDto userSchedule) {
        UsService.insert(userSchedule);
        return ResponseEntity.status(200).body(userSchedule);
    }

    //    update
    @PutMapping("/user/{seq}")
    public UsRequestDto update(@RequestBody UsRequestDto updateUserSchedule, @PathVariable Long seq) {
        UsService.update(updateUserSchedule, seq);
        return updateUserSchedule; //ResponseEntity.status(200).body(userSchedule);
    }

    //    delete
    @DeleteMapping("/user/{seq}")
    public ResponseEntity<?> delete(@PathVariable Long seq) {

        UsService.delete(seq);
        return ResponseEntity.status(200).body("삭제 완료");
    }

}
