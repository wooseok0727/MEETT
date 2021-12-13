package com.team.meett.service;

import com.team.meett.model.Users;
import org.springframework.stereotype.Service;


public interface UserService {

    public Users findByUsername(String username);

    public void insert(Users user);
    public void update(Users user);
    public void delete(String username);

}
