package com.team.meett.controller;

import com.team.meett.DTO.ErrorResponse;
import com.team.meett.model.Team;
import com.team.meett.service.TeamService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class TeamController {

    protected final TeamService teamService;

    //    find by title :: 팀명으로 조회했을 경우
    @GetMapping("/team")
    public ResponseEntity<?> selectTitle(@RequestParam(value = "title", required = false) String title) {
        List<Team> teamList;
        if (title != null) {
            teamList = teamService.findByTitle(title);
            if (teamList.isEmpty()) {
                return ResponseEntity.ok().body(title + "은 존재하지 않는 모임입니다");
            }
        } else {
            teamList = teamService.selectAll();
            if (teamList.isEmpty()) {
                return ResponseEntity.ok().body("Team이 존재하지 않습니다");
            }
        }
        return ResponseEntity.ok(teamList);
    }

    // get insert post insert
    @GetMapping("/team/create")
    public ResponseEntity<?> teamCreate() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/team/create")
    public ResponseEntity<?> teamCreate(@RequestBody Team team) {
        //DefaultHandlerExceptionResolver

        teamService.insert(team);
        return ResponseEntity.ok().body(team);
    }

    //update
    @PutMapping("/team/{teamId}")
    public ResponseEntity<?> update(@PathVariable String teamId) {
        Team team = teamService.findById(teamId);
        teamService.update(team);
        return ResponseEntity.ok().body(team);
    }

    @DeleteMapping("/team/{teamId}")
    public ResponseEntity<?> delete(@PathVariable String teamId) {
        teamService.delete(teamId);
        return ResponseEntity.ok().body("삭제완료");
    }


    //전역 예외처리
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> errorHandling(Exception e) {
        ErrorResponse response = new ErrorResponse();
        SimpleDateFormat timestamping = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
        String timestamp = timestamping.format(System.currentTimeMillis());

        if (e instanceof InvalidDataAccessApiUsageException) {
            //status 500
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("수정되는 데이터 값이 반드시 필요합니다::Entity must not be null.");
        } else if (e instanceof EmptyResultDataAccessException) {
            //status 500
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("삭제하고자 하는 TeamId가 존재하지 않습니다::No class entity with id exists!");
        } else if(e instanceof HttpMessageNotReadableException){
            //status 400
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("임시! insert하는 데이터 값이 JsonBody가 아닐때 발생됨::Required request body is missing");
        }
        response.setTimestamp(timestamp);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}
