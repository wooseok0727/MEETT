package com.team.meett.controller;

import com.team.meett.model.User;
import com.team.meett.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(String username, String password, HttpSession httpSession){

        User user = (User) userRepository.findByUsername(username);
        if(user == null){
            return ResponseEntity.status(400).body("아이디를 찾을 수 없습니다");
        }

        if(!password.equals(user.getPassword())){
            return ResponseEntity.status(400).body("비밀번호가 다릅니다" + password + "_" + user.getPassword());
        }
        httpSession.setAttribute("user", user);

        return ResponseEntity.status(200).body(user);
   }

    @PostMapping("/register")
    public ResponseEntity<?> register(User user){


        userRepository.save(user);
        return ResponseEntity.status(200).body(user);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession httpSession){
        httpSession.removeAttribute("user");
        return ResponseEntity.status(200).body("logout 성공");
    }

}
