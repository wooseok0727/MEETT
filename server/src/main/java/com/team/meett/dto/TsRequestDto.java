package com.team.meett.dto;


import com.team.meett.model.TeamSchedule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

//put post
@Getter
@Setter
@NoArgsConstructor
public class TsRequestDto {
    private Long seq;
    private String teamId;
    private String username;
    private String title;
    private String detail;
    private Date start;
    private Date end;
    private int role;

    public TeamSchedule toEntity(){
        return TeamSchedule.builder()
                .seq(seq)
                .team_id(teamId)
                .username(username)
                .title(title)
                .detail(detail)
                .start(start)
                .end(end)
                .role(role)
                .build();
    }
}