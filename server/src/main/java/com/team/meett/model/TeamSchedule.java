package com.team.meett.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_team_schedule")
public class TeamSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private Long seq;

    @Column(length = 50)
    private String team_id;

    @Column(length = 20)
    private String username;
    @Column(length = 20)
    private String title;
    @Column(length = 250)
    private String detail;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date start;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date end;

    @ColumnDefault("0")
    private int role;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    private Team team;

}
