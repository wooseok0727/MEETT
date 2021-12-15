package com.team.meett.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table(name = "tbl_team")
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @Id
    @Column(name = "team_id", length = 50)
    private String id;

    @Column(length = 20)
    private String username;

    @Column(length = 20)
    private String title;

    @Column(length = 20)
    private String password;

    @OneToMany(mappedBy = "team", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<TeamSchedule> teamScheduleList = new ArrayList<>();

}
