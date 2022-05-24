package br.com.tokunaga.moviegame.api.server.controller;

import br.com.tokunaga.moviegame.entites.Match;
import br.com.tokunaga.moviegame.entites.Movie;
import br.com.tokunaga.moviegame.services.IMatchService;
import br.com.tokunaga.moviegame.services.IMovieService;
import br.com.tokunaga.moviegame.services.IQuizzService;
import br.com.tokunaga.moviegame.services.IScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MatchGameController {
    private final IMatchService matchService;
    private final IMovieService movieService;
    private final IQuizzService quizzService;

    private final IScoreService scoreService;

    @GetMapping("/match/{uuid}")
    public ResponseEntity match(@PathVariable UUID uuid) {
        Match match = matchService.find(uuid);
        if (match == null) {
            return ResponseEntity.internalServerError().body("Match not found");
        }

        if (match.isValidMatch()) {
            if (match.isValidQuizz()) {
                return ResponseEntity.ok(
                        quizzService.generate(match.getCurrentQuizz())
                );
            } else {
                if (match.getNextQuizz().size() > 0) {
                    matchService.next(match);

                    return ResponseEntity.ok(
                            quizzService.generate(match.getCurrentQuizz())
                    );
                } else {
                    return ResponseEntity.ok("Match finished");
                }
            }
        } else {
            return ResponseEntity.ok("Match already finished");
        }
    }

    @GetMapping("/match/{uuid}/confirm/{id}")
    public ResponseEntity confirm(@PathVariable UUID uuid, @PathVariable Long id) {
        Match match = matchService.find(uuid);
        if (match == null) {
            return ResponseEntity.internalServerError().body("Match not found");
        }

        if (match.isValidMatch()) {
            if (match.isValidQuizz()) {
                if (match.isValidInput(id)) {
                    Movie movie = movieService.find(id);

                    boolean winner = movieService.isWinner(movie, match.getCurrentQuizz().getMovies());

                    match = matchService.update(match, winner);
                    scoreService.update(match, winner);

                    if (!match.isValidMatch()) {
                        return ResponseEntity.ok("You wrong 3 times, your match finished here.");
                    }

                    return ResponseEntity.ok("Quizz processed");
                } else {
                    return ResponseEntity.unprocessableEntity().body("Id not recognize");
                }
            } else {
                return ResponseEntity.unprocessableEntity().body("Quizz already processed");
            }
        } else {
            return ResponseEntity.unprocessableEntity().body("Match already finished");
        }
    }
}
