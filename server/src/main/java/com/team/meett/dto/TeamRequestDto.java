package com.team.meett.dto;

import com.team.meett.model.Team;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//PUT, POST 요청에 대해
@Getter
@Setter
@NoArgsConstructor
public class TeamRequestDto {
    private String teamId;
    private String title;
    private String username;
    private String password;

    @Builder
    public TeamRequestDto(String title, String username, String password) {
        this.title = title;
        this.username = username;
        this.password = password;
    }

    public Team toEntity() {
        return Team.builder()
                .title(title)
                .username(username)
                .password(password)
                .build();
    }
}
