package com.team.meett.controller;

import com.team.meett.dto.JwtRequest;
import com.team.meett.dto.JwtResponse;
import com.team.meett.config.JwtRequestFilter;
import com.team.meett.config.JwtTokenUtil;
import com.team.meett.model.Users;
import com.team.meett.service.JwtUserDetailService;
import com.team.meett.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final JwtUserDetailService userDetailService;

    private final UserService userService;

    @Autowired
    public UserController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,
                          JwtUserDetailService userDetailService,UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailService = userDetailService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest jwtRequest) throws  Exception {

        try {
            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        final UserDetails userDetails =
                userDetailService.loadUserByUsername(jwtRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        HttpHeaders headers = new HttpHeaders();
        headers.add(JwtRequestFilter.AUTHORIZATION_HEADER,"Bearer " + token);

        Users user = userService.findById(jwtRequest.getUsername());

        return new ResponseEntity<>(new JwtResponse(user.getUsername(),user.getNickname(),token),headers,HttpStatus.OK);
    }

    @PostMapping("/register" )
    public ResponseEntity<?> register(@RequestBody Users user) {
        if(userService.existsById(user.getUsername())) {
            return ResponseEntity.badRequest().body("이미 존재하는 아이디입니다");
        }
        userService.insert(user);
        return ResponseEntity.ok().body("회원가입 성공");
    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
            //비밀번호 매치 코드
        } catch (DisabledException e) {
            throw new Exception("사용자 비활성화", e);
        } catch (BadCredentialsException e) {
            throw new Exception("비밀번호가 틀렸습니다",e);
        } catch (UsernameNotFoundException e) {
            throw new Exception("존재하지 않는 사용자입니다",e);
        }
    }
}
