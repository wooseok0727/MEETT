package com.team.meett.controller;

import com.team.meett.repository.UserRepository;
import com.team.meett.repository.UserScheduleRepository;
import com.team.meett.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("test")
@RestController
public class testController {

    protected final TestService testService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserScheduleRepository userScheduleRepository;

    public testController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/test")
    public ResponseEntity<?> test1(){
        testService.test();
        return ResponseEntity.status(200).body("test success!!");

    }

    @GetMapping("/test2")
    public ResponseEntity test2() {


        return ResponseEntity.status(200).body("success");
    }



    @GetMapping("/user")
    public ResponseEntity<?> test() {

        ResponseEntity<String> response = ResponseEntity.status(200).body("hello!!!");
        return response;
    }
}
