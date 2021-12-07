package com.team.meett.service;

import com.team.meett.model.UserSchedule;
import com.team.meett.model.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class TestService {

    private final EntityManager em;

    @Transactional
    public void test() {
        Users user = Users.builder()
                .username("admin2")
                .password("1234")
                .email("admin2@naver.com")
                .nickname("ADMIN_2").build();
        em.persist(user);
    }

    @Transactional
    public void test2(){

        Users user = new Users();
        user.setUsername("test33");
        user.setEmail("testEmail");
        user.setNickname("testNN");
        user.setPassword("testPassword");

        em.persist(user);

        Date date = new Date();
        UserSchedule userSchedule = new UserSchedule();
        userSchedule.setUsername("scheduleTest");
        userSchedule.setDetail("testDetail");
        userSchedule.setEnd(date);
        userSchedule.setStart(date);
        userSchedule.setTitle("testTitle");
        userSchedule.setUsers(user);
        em.persist(userSchedule);
    }


}
