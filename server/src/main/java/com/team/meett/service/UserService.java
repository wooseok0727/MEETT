package com.team.meett.service;

import com.team.meett.model.Users;


public interface UserService {

    public Users findById(String username);
    public boolean existsById(String username);

    public void insert(Users user);
    public void update(Users user);
    public void delete(String username);

}
