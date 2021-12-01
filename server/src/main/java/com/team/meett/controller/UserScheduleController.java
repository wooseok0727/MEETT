package com.team.meett.controller;

import com.team.meett.model.UserSchedule;
import com.team.meett.service.UserScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class UserScheduleController {

    protected final UserScheduleService UsService;

    //    select findByUsername
    @GetMapping("/user/{username}")
    public ResponseEntity<?> selectUsername(@PathVariable String username) {
        List<UserSchedule> UsList = UsService.findByUsername(username);
        log.debug(UsList.toString());
        return ResponseEntity.status(200).body(UsList);
    }


    //    insert get-mapping post-mapping
    @GetMapping("/user")
    public ResponseEntity<?> insert() {

        return ResponseEntity.status(200).build();
    }

    @PostMapping("/user")
    public ResponseEntity<?> insert(UserSchedule userSchedule) {
        UsService.insert(userSchedule);
        return ResponseEntity.status(200).body(userSchedule);
    }

//    update
    @PatchMapping("/user/{seq}")
    public ResponseEntity<?> update(@PathVariable Long seq){
        UserSchedule userSchedule = UsService.findById(seq);
        // 업데이트 확인용 예시 데이터 userSchedule.setUsername("update");
        UsService.update(userSchedule);
        return ResponseEntity.status(200).body(UsService.selectAll());
    }

//    delete
    @DeleteMapping("/user/{seq}")
    public ResponseEntity<?> delete(@PathVariable Long seq){
        UsService.delete(seq);
        return ResponseEntity.status(200).body("삭제 완료");
    }
}
