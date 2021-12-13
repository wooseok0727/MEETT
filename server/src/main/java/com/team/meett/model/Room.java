package com.team.meett.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    private String username;
    private String teamId;

    @ManyToOne
    @JoinColumn(name = "username", insertable = false, updatable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "teamId", insertable = false, updatable = false)
    private Team team;
}
