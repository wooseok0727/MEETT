package com.team.meett.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtResponse {

    private final String username;
    private final String nickname;
    private final String token;

}
