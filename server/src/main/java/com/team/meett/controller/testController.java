package com.team.meett.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("test")
@RestController
public class testController {

    @GetMapping("/user")
    public ResponseEntity<?> test() {

        ResponseEntity<String> response = ResponseEntity.status(200).body("hello!!!");
        return response;
    }
}
