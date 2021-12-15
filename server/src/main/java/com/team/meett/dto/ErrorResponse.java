package com.team.meett.dto;

import lombok.*;

import java.text.SimpleDateFormat;

//@AllArgsConstructor
//@ToString
//@Builder
@Getter
@Setter
public class ErrorResponse {

    public ErrorResponse() {
        SimpleDateFormat timestamping = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
        this.timestamp = timestamping.format(System.currentTimeMillis());
    }

    private int statusCode;
    private String message;
    private String timestamp;
}
