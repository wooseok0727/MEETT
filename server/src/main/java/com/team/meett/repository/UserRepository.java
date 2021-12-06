package com.team.meett.repository;

import com.team.meett.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

    public Users findByUsername(String username);

}
