package com.team.meett.service;

import com.team.meett.model.Team;

import java.util.List;

public interface SearchService {

    public List<Team> searchByTitle(String title);

}
