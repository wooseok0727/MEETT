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

    @Column(length = 20)
    private String username;

    @Column(length = 50)
    private String team_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", insertable = false, updatable = false)
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    private Team team;
}
