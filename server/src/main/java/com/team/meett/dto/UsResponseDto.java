package com.team.meett.dto;

import com.team.meett.model.UserSchedule;
import lombok.Getter;

import java.util.Date;

//GET
@Getter
public class UsResponseDto {

    private final Long seq;
    private final String username;
    private final String title;
    private final String detail;
    private final Date start;
    private final Date end;
    private final int role;

    public UsResponseDto(UserSchedule schedule){
        this.seq = schedule.getSeq();
        this.username = schedule.getUsername();
        this.title = schedule.getTitle();
        this.detail = schedule.getDetail();
        this.start = schedule.getStart();
        this.end = schedule.getEnd();
        this.role = schedule.getRole();
    }

}
