package com.team.meett.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "tbl_schedule", schema = "MeettDB")
public class UserSchedule {

    @Id
    @Column(name = "seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

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
    @JoinColumn(name = "username", insertable = false, updatable = false)
    private Users users;

}
