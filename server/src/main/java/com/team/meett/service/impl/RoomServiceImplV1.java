package com.team.meett.service.impl;

import com.team.meett.model.Room;
import com.team.meett.repository.RoomRepository;
import com.team.meett.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImplV1 implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public List<Room> selectAll() {

        return roomRepository.findAll();
    }

    @Override
    public void insert(Room room) {
        roomRepository.save(room);
    }

    // return 값 논의 필요
    @Override
    public void update(Room room, Long seq) {
        if (roomRepository.existsById(seq)) {
            roomRepository.save(room);
        } else throw new EmptyResultDataAccessException(1);
    }

    // return 값 논의 필요
    @Override
    public void delete(Long seq) {
        if (roomRepository.existsById(seq)) {
            roomRepository.deleteById(seq);
        } else throw new EmptyResultDataAccessException(1);
    }

    @Override
    public Room findById(Long seq) {
        Optional<Room> room = roomRepository.findById(seq);// 왜 Optional로 해야하는지?
        return room.orElse(null);
    }

    @Override
    public List<Room> findByUsername(String username) {
        return roomRepository.findByUsername(username);
    }

    @Override
    public List<Room> findByTeamId(String teamId) {
        return roomRepository.findByTeamId(teamId);
    }

    @Override
    public Room findByUsernameAndTeamId(String username, String teamId) {
        return roomRepository.findByUsernameAndTeamId(username, teamId);
    }
}
