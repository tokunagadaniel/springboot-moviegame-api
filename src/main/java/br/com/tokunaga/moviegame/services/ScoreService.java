package br.com.tokunaga.moviegame.services;

import br.com.tokunaga.moviegame.entites.Match;
import br.com.tokunaga.moviegame.entites.Score;
import br.com.tokunaga.moviegame.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScoreService implements IScoreService {

    private final ScoreRepository repository;

    @Override
    public String get(String Username) {

    }

    @Override
    public List<String> getAll() {

    }

    @Override
    public void update(Match match, boolean winner) {
        int counter = winner ? 1 : 0;

        Optional<Score> score = repository.findById(match.getUser().getName());

        if (score.isPresent()) {
            Score current = score.get();
            repository.save(
                    Score.builder().
                            rightAnswer(current.getRightAnswer() + counter)
                            .quizzAnswer(current.getQuizzAnswer() + 1)
                            .build()
            );
        } else {
            repository.save(
                    Score.builder().
                            rightAnswer(winner ? 1 : 0)
                            .quizzAnswer(1)
                            .build()
            );
        }
    }
}
