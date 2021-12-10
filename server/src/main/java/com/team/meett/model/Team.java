package com.team.meett.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    @Column(name = "teamId")
    private String teamId;

    private String username;
    private String title;
    private String password;

    @OneToMany(mappedBy = "team")
    @JsonBackReference
    private List<TeamSchedule> teamScheduleList = new ArrayList<>();
}
