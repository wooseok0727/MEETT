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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "username")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "teamId")
    private Team team;
}
