package com.team.meett.controller;

import com.team.meett.dto.UsRequestDto;
import com.team.meett.dto.UsResponseDto;
import com.team.meett.service.UserScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:3000")
public class UserScheduleController {

    protected final UserScheduleService UsService;

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


//    //    insert get-mapping post-mapping
//    @GetMapping("/user")
//    public ResponseEntity<?> insert() {
//
//        return ResponseEntity.status(200).build();
//    }

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
        if(UsService.delete(seq) == 1){
            return ResponseEntity.status(200).body("삭제 완료");
        }
        throw new EmptyResultDataAccessException(1); //임시코드 수정필요
    }

}
