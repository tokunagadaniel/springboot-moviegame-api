package br.com.tokunaga.moviegame.services;

import br.com.tokunaga.moviegame.entites.Movie;
import br.com.tokunaga.moviegame.entites.Quizz;
import br.com.tokunaga.moviegame.repository.QuizzRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizzService implements IQuizzService {

    private final QuizzRepository repository;

    @Override
    public String generate(Quizz quizz) {
        String res = "";

        for (Movie aux : quizz.getMovies()) {
            res += aux.getId() + ". " + aux.getName() + "\n";
        }

        return res;
    }
}
