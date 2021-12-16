package com.team.meett.config;

import com.team.meett.service.JwtUserDetailService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUserDetailService jwtUserDetailService;

    private final JwtTokenUtil jwtTokenUtil;

    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Autowired
    public JwtRequestFilter(JwtUserDetailService jwtUserDetailService, JwtTokenUtil jwtTokenUtil) {
        this.jwtUserDetailService = jwtUserDetailService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {



        final String requestTokenHeader = request.getHeader(AUTHORIZATION_HEADER);

        String username = null;
        String jwtToken = null;

        // JWT 토큰은 "Bearer token" 형식. Bearer 단어를 제거하고 토큰만 가져오기
        log.debug(requestTokenHeader);
        if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            log.debug(jwtToken);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                log.debug("JWT 토큰을 가져올 수 없습니다.");
            } catch ( ExpiredJwtException e) {
                log.debug("JWT 토큰이 만료되었습니다");
            }
        } else {
            logger.debug("JWT 토큰이 Bearer 로 시작하지 않습니다");
        }

        // 토큰을 받으면 유효성을 검사
        if( username != null && SecurityContextHolder.getContext().getAuthentication() == null ) {

            UserDetails userDetails = this.jwtUserDetailService.loadUserByUsername(username);

            // 토큰이 유효한 경우 수동으로 인증을 설정하도록 Spring Security 를 구성
            if(jwtTokenUtil.validateToken(jwtToken,userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // 컨텍스트에서 인증을 설정한 후 현재 사용자가 인증되었음을 지정.
                // 따라서 Spring Security Configurations 를 성공적으로 통과
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
