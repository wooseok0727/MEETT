package com.team.meett.service;

import com.team.meett.model.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class JwtUserDetailService implements UserDetailsService {

    private final UserService userService;

    public JwtUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userService.findById(username);
        if(user.getUsername().equals(username)) {
            return new User(user.getUsername(),"{noop}" + user.getPassword(),new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("사용자 정보를 찾을 수 없습니다 : " + username);
        }

    }
}
