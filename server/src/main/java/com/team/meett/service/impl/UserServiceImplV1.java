package com.team.meett.service.impl;

import com.team.meett.model.Users;
import com.team.meett.repository.UserRepository;
import com.team.meett.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service("UserService")
@RequiredArgsConstructor
public class UserServiceImplV1 implements UserService {

    private final UserRepository userRepository;

    @Override
    public Users findByUsername(String username) {
        Users users = userRepository.findByUsername(username);
        return users;
    }

    @Override
    public void insert(Users user) {
        userRepository.save(user);
    }

    @Override
    public void update(Users user) {
        userRepository.save(user);
    }

    @Override
    public void delete(String username) {
        userRepository.deleteById(username);
    }
}
