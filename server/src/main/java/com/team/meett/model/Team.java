package com.team.meett.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "tbl_team")
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "t_teamId")
    private String t_teamId;

    private String t_username;
    private String t_title;
    private String t_password;

    @OneToMany(mappedBy = "team")
    private List<TeamSchedule> teamScheduleList = new ArrayList<>();
}
