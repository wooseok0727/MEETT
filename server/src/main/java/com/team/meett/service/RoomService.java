package com.team.meett.service;


import com.team.meett.model.Room;

import java.util.List;

public interface RoomService {

    public List<Room> selectAll();

    public Room findById(Long seq);
    public List<Room> findByUsername(String username);
    public List<Room> findByTeamId(String teamId);

    public Room findByUsernameAndTeamId(String username, String teamId);

    public void insert(Room room);
    public void update(Room room, Long seq);
    public void delete(Long seq);

}
