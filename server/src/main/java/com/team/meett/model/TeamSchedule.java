package com.team.meett.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter

@Entity
@Table(name = "tbl_team_schedule")
public class TeamSchedule {
    @Id
    @GeneratedValue
    @Column(name = "ts_seq")
    private Long ts_seq;

    private String ts_username;
    private String ts_title;
    private String ts_detail;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(nullable = false)
    private Date ts_start;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(nullable = false)
    private Date ts_end;

    @ColumnDefault("0")
    private int ts_role;

    @ManyToOne
    @JoinColumn(name = "t_teamId", insertable = false, updatable = false)
    private Team team;

}
