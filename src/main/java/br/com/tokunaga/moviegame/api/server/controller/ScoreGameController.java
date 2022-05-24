package br.com.tokunaga.moviegame.api.server.controller;

import br.com.tokunaga.moviegame.services.IScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScoreGameController {
    private final IScoreService iScoreService;

    @GetMapping("/score")
    public ResponseEntity ranking() {
        return ResponseEntity.ok(
                iScoreService.getAll()
        );
    }

    @GetMapping("/score/{username}")
    public ResponseEntity ranking(@PathVariable String username) {
        return ResponseEntity.ok(
                iScoreService.get(username)
        );
    }
}
