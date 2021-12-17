package com.team.meett.service.impl;

import com.team.meett.model.Users;
import com.team.meett.repository.UserRepository;
import com.team.meett.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service("UserService")
@RequiredArgsConstructor
public class UserServiceImplV1 implements UserService {

    private final UserRepository userRepository;


    @Override
    public Users findById(String username) {
        return userRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 사용자입니다"));
    }

    @Override
    public boolean existsById(String username) {
        return userRepository.existsById(username);
    }

    @Override
    public void insert(Users user) {
        userRepository.save(user);
    }

    // existById 추가??
    @Override
    public void update(Users user) {
        userRepository.save(user);
    }

    @Override
    public void delete(String username) {
        userRepository.deleteById(username);
    }
}
