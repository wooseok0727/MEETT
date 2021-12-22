package com.team.meett.dto;


import com.team.meett.model.Team;
import lombok.Getter;

//GET 요청에 대해
@Getter //분별없는 수정으로 데이터가 손상되지 않도록 setter를 제외하고 getter만 선언
public class TeamResponseDto {
    private final String teamId;
    private final String title;
    private final String username;
    private final String password;

    public TeamResponseDto(Team team) {
        this.teamId = team.getId();
        this.title = team.getTitle();
        this.username = team.getUsername();
        this.password = team.getPassword();
    }


}
