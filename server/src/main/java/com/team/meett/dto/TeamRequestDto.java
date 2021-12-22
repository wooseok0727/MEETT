package com.team.meett.dto;

import com.team.meett.model.Team;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

//PUT, POST 요청에 대해
@Getter
@Setter
@NoArgsConstructor
@ToString
@Slf4j
public class TeamRequestDto {
    private String teamId;
    private String title;
    private String username;
    private String password;

    public Team toEntity() {

        return Team.builder()
                .id(teamId)
                .title(title)
                .username(username)
                .password(password)
                .build();
    }
}
