package com.team.meett.repository;

import com.team.meett.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    public List<Room> findByUsername(String username);
    public List<Room> findByTeamId(String teamId);

    public Room findByUsernameAndTeamId(String username, String teamId);

}


