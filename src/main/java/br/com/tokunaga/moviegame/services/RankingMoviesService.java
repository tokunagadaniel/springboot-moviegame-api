package br.com.tokunaga.moviegame.services;

import br.com.tokunaga.moviegame.api.client.RankingMoviesApi;
import br.com.tokunaga.moviegame.entites.Movie;
import br.com.tokunaga.moviegame.repository.MovieRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingMoviesService implements IRankingMoviesService {
    private final MovieRepository repository;
    private final RankingMoviesApi api;

    @Override
    public void load() {
        List<Movie> movies = repository.findAll();

        if (movies.size() == 0) {
            Iterator<JsonNode> iterator = api.getRakingMovies().withArray("results").elements();

            while (iterator.hasNext()) {
                JsonNode jn = iterator.next();

                repository.save(
                        Movie.builder()
                                .name(jn.get("title").asText())
                                .rating(jn.get("rating").asText())
                                .build()
                );
            }
        }
    }
}
