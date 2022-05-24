package br.com.tokunaga.moviegame.services;

import br.com.tokunaga.moviegame.entites.*;
import br.com.tokunaga.moviegame.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static br.com.tokunaga.moviegame.entites.Status.ACTIVE;
import static br.com.tokunaga.moviegame.entites.Status.DONE;

@Service
@RequiredArgsConstructor
public class MatchService implements IMatchService {
    private final MatchRepository repository;

    @Override
    public Match find(UUID uuid) {
        Optional<Match> optMatch = repository.findById(uuid);
        if (optMatch.isPresent()) {
            return optMatch.get();
        }
        return null;
    }

    @Override
    public List<Match> findByUser(String name) {
        return repository.findByUser(name);
    }

    @Override
    public Match findByUserAndStatus(String name, Status status) {
        return repository.findByUserAndStatus(name, status.ordinal());
    }

    @Override
    public void next(Match match) {
        List<Quizz> list = new ArrayList<>();

        list.add(match.getCurrentQuizz());
        list.addAll(match.getHistoryQuizz());

        match.setHistoryQuizz(list);

        Quizz quizz = match.getNextQuizz().remove(0);
        match.setCurrentQuizz(quizz);

        repository.save(match);
    }

    @Override
    public Match update(Match match, boolean win) {
        Quizz quizz = match.getCurrentQuizz();
        quizz.setStatus(DONE);

        if (win) {
            match.setRightAnswer(match.getRightAnswer() + 1);
        } else {
            match.setErrorAnswer(match.getErrorAnswer() + 1);
        }

        if (match.getErrorAnswer() == 3) {
            match.setStatus(DONE);
        }

        match.setCurrentQuizz(quizz);

        return repository.save(match);
    }

    @Override
    public UUID start(User user, List<Movie> movies) {
        List<Movie> list = new ArrayList<>();
        List<Quizz> quizzes = new ArrayList<>();

        for (Movie aux : movies) {
            if (list.size() < 2) {
                list.add(aux);
            }

            if (list.size() == 2) {
                quizzes.add(
                        Quizz.builder()
                                .status(ACTIVE)
                                .movies(new ArrayList<>(list))
                                .build()
                );

                list.clear();
            }
        }

        Quizz quizz = quizzes.remove(0);

        Match match = Match.builder()
                .id(UUID.randomUUID())
                .currentQuizz(quizz)
                .nextQuizz(quizzes)
                .errorAnswer(0)
                .rightAnswer(0)
                .user(user)
                .status(ACTIVE)
                .build();

        repository.save(match);

        return match.getId();
    }

    @Override
    public void finish(Match match) {
        match.setStatus(DONE);
        repository.save(match);
    }
}
