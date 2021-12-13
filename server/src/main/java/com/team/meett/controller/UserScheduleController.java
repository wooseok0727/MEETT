package com.team.meett.controller;

import com.team.meett.DTO.ErrorResponse;
import com.team.meett.model.UserSchedule;
import com.team.meett.service.UserScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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

        List<UserSchedule> UsList = UsService.findByUsername(username);
        log.debug(UsList.toString());
        if (UsList.isEmpty()) {
            return ResponseEntity.ok().body("데이터 없음");
        }
        return ResponseEntity.ok(UsList);
    }


    //    insert get-mapping post-mapping
    @GetMapping("/user")
    public ResponseEntity<?> insert() {

        return ResponseEntity.status(200).build();
    }

    @PostMapping("/user")
    public ResponseEntity<?> insert(@RequestBody UserSchedule userSchedule) {
        UsService.insert(userSchedule);
        return ResponseEntity.status(200).body(userSchedule);
    }

    //    update
    @PutMapping("/user/{seq}")
    public ResponseEntity<?> update(@PathVariable Long seq) {
        UserSchedule userSchedule = UsService.findById(seq);
        UsService.update(userSchedule);
        return ResponseEntity.status(200).body(userSchedule);
    }

    //    delete
    @DeleteMapping("/user/{seq}")
    public ResponseEntity<?> delete(@PathVariable Long seq) {
        if(UsService.delete(seq) == 1){
            return ResponseEntity.status(200).body("삭제 완료");
        }
        throw new EmptyResultDataAccessException(1); //임시코드 수정필요
    }

    //예외처리
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> errorHandling(Exception e) {
        ErrorResponse response = new ErrorResponse();

        if (e instanceof InvalidDataAccessApiUsageException) {
            //status 500
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("수정되는 데이터 값이 반드시 필요합니다::Entity must not be null.");
        } else if (e instanceof EmptyResultDataAccessException) {
            //status 500
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("삭제하고자 하는 Id가 존재하지 않습니다::No class entity with id exists!");
        } else if(e instanceof HttpMessageNotReadableException){
            //status 400
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("임시! insert하는 데이터 값이 JsonBody가 아닐때 발생됨::Required request body is missing");
        } else if(e instanceof MethodArgumentTypeMismatchException){
            //status 400
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("seq의 칼럼 타입은 Long입니다. 그 외의 다른 타입이 들어감::Failed to convert value of type");
        }
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
