package com.team.meett.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private Long seq;

    private String username;
    private String title;
    private String detail;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(nullable = false)
    private Date start;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(nullable = false)
    private Date end;

    @ColumnDefault("0")
    private int role;


    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "team_id", insertable = false, updatable = false)
    private Team team;

}
