package br.com.tokunaga.moviegame.api.server.controller;

import br.com.tokunaga.moviegame.entites.Match;
import br.com.tokunaga.moviegame.entites.Movie;
import br.com.tokunaga.moviegame.entites.User;
import br.com.tokunaga.moviegame.services.IMatchService;
import br.com.tokunaga.moviegame.services.IMovieService;
import br.com.tokunaga.moviegame.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static br.com.tokunaga.moviegame.entites.Status.ACTIVE;

@RestController
@RequiredArgsConstructor
public class StartGameController {
    private final IMatchService matchService;
    private final IMovieService movieService;
    private final IUserService userService;

    @GetMapping("/start")
    public ResponseEntity start() {
        List<Movie> movies = movieService.find();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Match current = matchService.findByUserAndStatus(auth.getName(), ACTIVE);
        if (current == null) {
            return ResponseEntity.internalServerError().body("Match not found");
        }

        if (current == null) {
            User user = userService.find(auth.getName());

            if (user == null) {
                return ResponseEntity.internalServerError().body("User not found");
            }

            UUID uuid = matchService.start(user, movies);

            return ResponseEntity.ok("Match started: " + uuid);
        } else {
            return ResponseEntity.ok("Match already started: " + current.getId());
        }
    }
}
