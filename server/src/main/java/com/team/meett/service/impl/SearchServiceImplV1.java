package com.team.meett.service.impl;

import com.team.meett.model.Team;
import com.team.meett.repository.TeamRepository;
import com.team.meett.service.SearchService;
import com.team.meett.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("SearchService")
@RequiredArgsConstructor
public class SearchServiceImplV1 implements SearchService {

    private final TeamRepository teamRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Team> searchByTitle(String title) {
        List<Team> teamList = em.createQuery("select t from Team t where t.title like '%title%'", Team.class)
                .setParameter(title, "title")
                .getResultList();
        return teamList;
    }
}
