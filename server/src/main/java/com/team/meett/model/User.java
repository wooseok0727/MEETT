package com.team.meett.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    @Column(length = 20)
    private String username;

    @Column(length = 20, nullable = false)
    private String password;

    @Column(length = 10, nullable = false)
    private String nickname;

    @Column(length = 30, nullable = false)
    private String email;

}
