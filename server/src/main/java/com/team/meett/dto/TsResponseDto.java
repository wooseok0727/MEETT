package com.team.meett.dto;


import com.team.meett.model.TeamSchedule;
import lombok.Getter;

import java.util.Date;

//put post
@Getter
public class TsResponseDto {
   private final Long seq;
   private final String teamId;
   private final String username;
   private final String title;
   private final String detail;
   private final Date start;
   private final Date end;
   private final int role;


   public TsResponseDto(TeamSchedule teamSchedule){
       this.seq = teamSchedule.getSeq();
       this.teamId = teamSchedule.getTeam_id();
       this.username = teamSchedule.getUsername();
       this.title = teamSchedule.getTitle();
       this.detail = teamSchedule.getDetail();
       this.start = teamSchedule.getStart();
       this.end = teamSchedule.getEnd();
       this.role = teamSchedule.getRole();
   }
}
