package com.team.meett.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter


@Entity
@Table(name = "tbl_user", schema = "MeettDB")
public class Users {

    @Id
    @Column(length = 20,name = "username")
    private String username;

    @Column(length = 20)
    private String password;

    @Column(length = 10)
    private String nickname;

    @Column(length = 30)
    private String email;

    @OneToMany(mappedBy = "users")
    @JsonManagedReference
    private List<UserSchedule> userScheduleList = new ArrayList<>();

}
