package br.com.tokunaga.moviegame.services;

import br.com.tokunaga.moviegame.entites.Match;

import java.util.List;

public interface IScoreService {
    String get(String Username);

    List<String> getAll();

    void update(Match match, boolean winner);
}
