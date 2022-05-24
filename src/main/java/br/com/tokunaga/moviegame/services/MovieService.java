package br.com.tokunaga.moviegame.services;

import br.com.tokunaga.moviegame.entites.Movie;
import br.com.tokunaga.moviegame.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService implements IMovieService {
    private final MovieRepository repository;

    @Override
    public Movie find(Long id) {
        Optional<Movie> optMovie = repository.findById(id);
        if (optMovie.isPresent()) {
            return optMovie.get();
        }
        return null;
    }

    @Override
    public List<Movie> find() {
        List<Movie> movies = repository.findAll();

        Collections.shuffle(movies);

        return movies.stream()
                .collect(Collectors.toList());
    }

    @Override
    public boolean isWinner(Movie current, List<Movie> movies) {
        for (Movie aux : movies) {
            if (current.getId().equals(aux.getId())) {
                continue;
            } else {
                return toRankingDouble(current.getRating()) > toRankingDouble(aux.getRating());
            }
        }
        return false;
    }

    private double toRankingDouble(String ranking) {
        return Double.parseDouble(ranking.substring(0, ranking.length() - 3));
    }
}
