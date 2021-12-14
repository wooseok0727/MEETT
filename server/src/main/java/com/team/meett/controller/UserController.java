package com.team.meett.controller;

import com.team.meett.model.Users;
import com.team.meett.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    protected final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users userInfo, HttpSession httpSession){

        Users user = userService.findByUsername(userInfo.getUsername());
        log.debug(">>>>username" + user);
        if(user == null){
            return ResponseEntity.status(400).body("아이디를 찾을 수 없습니다");
        }

        if(!userInfo.getPassword().equals(user.getPassword())){
            return ResponseEntity.status(400).body("비밀번호가 다릅니다" + userInfo.getPassword() + "_" + user.getPassword());
        }
        httpSession.setAttribute("user", user);

        return ResponseEntity.status(200).body(user);
   }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Users user){

        log.debug(user.toString());
        userService.insert(user);
        return ResponseEntity.status(200).body(user);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession httpSession){
        httpSession.removeAttribute("user");
        log.debug(">>>> 로그아웃 성공");
        return ResponseEntity.status(200).body("logout 성공");
    }


}
