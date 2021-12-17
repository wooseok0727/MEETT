package com.team.meett.dto;

import com.team.meett.model.UserSchedule;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

//put, post
@Getter
@Setter
public class UsRequestDto {
    private String username;
    private String title;
    private String detail;
    private Date start;
    private Date end;
    private int role;

//    @Builder
//    public UsRequestDto(String username, String title, String detail, Date start, Date end, int role){
//        this.username = username;
//        this.title = title;
//        this.detail = detail;
//        this.start = start;
//        this.end = end;
//        this.role = role;
//    }

    public UserSchedule toEntity(){
        return UserSchedule.builder()
                .username(username)
                .title(title)
                .detail(detail)
                .start(start)
                .end(end)
                .build();
    }


}
