package com.team.meett.service;

import com.team.meett.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class TestService {
    private final EntityManager em;

    @Transactional
    public void test() {
        User user = User.builder()
                .username("admin")
                .password("1234")
                .email("admin@naver.com")
                .nickname("ADMIN_").build();
        em.persist(user);
    }
}
