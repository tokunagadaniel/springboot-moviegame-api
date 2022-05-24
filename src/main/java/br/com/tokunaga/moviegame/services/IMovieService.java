package br.com.tokunaga.moviegame.services;

import br.com.tokunaga.moviegame.entites.Movie;

import java.util.List;

public interface IMovieService {
    Movie find(Long id);

    List<Movie> find();

    boolean isWinner(Movie current, List<Movie> movies);
}
