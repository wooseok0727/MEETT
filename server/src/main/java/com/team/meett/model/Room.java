package com.team.meett.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name = "tbl_room")
public class Room {
    @Id
    @GeneratedValue
    @Column(name = "seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_username")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "team_t_teamId")
    private Team team;
}
