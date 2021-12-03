package com.team.meett.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tbl_user", schema = "MeettDB")
public class User {

    @Id
    @Column(length = 20, name = "username")
    private String username;

    @Column(length = 20, nullable = false)
    private String password;

    @Column(length = 10, nullable = false)
    private String nickname;

    @Column(length = 30, nullable = false)
    private String email;

    @OneToMany(mappedBy = "user")
    List<UserSchedule> schedules = new ArrayList<>();

}
