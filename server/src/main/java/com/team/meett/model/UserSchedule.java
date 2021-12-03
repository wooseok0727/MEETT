package com.team.meett.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

@Entity
@Table(name = "tbl_schedule", schema = "MeettDB")
public class UserSchedule {
    @Id
    @Column(name = "seq", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(length = 20, nullable = false)
    private String username;

    @Column(length = 20, nullable = false)
    private String title;

    @Column(length = 255, nullable = false)
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
    @JoinColumn(name = "username_id")
    private User user;

}
