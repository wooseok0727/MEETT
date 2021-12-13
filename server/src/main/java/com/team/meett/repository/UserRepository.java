package com.team.meett.repository;

import com.team.meett.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users, String> {

    public Users findByUsername(String username);

}
