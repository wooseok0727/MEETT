package com.team.meett.dto;

import com.team.meett.model.Team;
import lombok.Getter;

@Getter
public class SearchTeamResponseDto {

    private final String teamId;
    private final String title;
    private final String username;

    public SearchTeamResponseDto(Team team) {
        this.teamId = team.getId();
        this.title = team.getTitle();
        this.username = team.getUsername();
    }
}
