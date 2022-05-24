package br.com.tokunaga.moviegame.services;

import br.com.tokunaga.moviegame.entites.Match;
import br.com.tokunaga.moviegame.entites.Movie;
import br.com.tokunaga.moviegame.entites.Status;
import br.com.tokunaga.moviegame.entites.User;

import java.util.List;
import java.util.UUID;

public interface IMatchService {

    Match find(UUID uuid);

    List<Match> findByUser(String name);
    Match findByUserAndStatus(String name, Status status);

    void next(Match match);

    Match update(Match match, boolean win);

    UUID start(User user, List<Movie> movies);

    void finish(Match match);
}
