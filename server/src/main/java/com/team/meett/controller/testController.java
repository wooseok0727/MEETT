package com.team.meett.controller;

import com.team.meett.model.User;
import com.team.meett.service.TestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("test")
@RestController
public class testController {

    protected final TestService testService;

    public testController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/test")
    public ResponseEntity<?> test2(){
        User user = null;
        testService.test();
        return ResponseEntity.status(200).body(user);

    }

    @GetMapping("/user")
    public ResponseEntity<?> test() {

        ResponseEntity<String> response = ResponseEntity.status(200).body("hello!!!");
        return response;
    }
}
