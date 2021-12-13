package com.team.meett.DTO;

import lombok.Data;

@Data
public class ErrorResponse {
    private int statusCode;
    private String message;
    private String timestamp;
}
