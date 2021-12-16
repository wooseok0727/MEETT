package com.team.meett.controller;

import com.team.meett.dto.JwtRequest;
import com.team.meett.dto.JwtResponse;
import com.team.meett.config.JwtRequestFilter;
import com.team.meett.config.JwtTokenUtil;
import com.team.meett.service.JwtUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final JwtUserDetailService userDetailService;

    @Autowired
    public JwtAuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, JwtUserDetailService userDetailService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailService = userDetailService;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToKen(@RequestBody JwtRequest authenticationRequest) throws  Exception {

        authenticate(authenticationRequest.getUsername(),authenticationRequest.getPassword());

        final UserDetails userDetails =
                userDetailService.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        HttpHeaders headers = new HttpHeaders();
        headers.add(JwtRequestFilter.AUTHORIZATION_HEADER,"Bearer " + token);
        return new ResponseEntity<>(new JwtResponse(token),headers, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        } catch (DisabledException e) {
            throw new Exception("사용자 비활성화", e);
        } catch (BadCredentialsException e) {
            throw new Exception("잘못된 자격 증명",e);
        }
    }
}
