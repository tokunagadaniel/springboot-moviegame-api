package br.com.tokunaga.moviegame.api.server.controller;

import br.com.tokunaga.moviegame.entites.Match;
import br.com.tokunaga.moviegame.services.IMatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class FinishGameController {
    private final IMatchService matchService;

    @GetMapping("/finish/{uuid}")
    public ResponseEntity finish(@PathVariable UUID uuid) {
        Match match = matchService.find(uuid);
        if (match == null) {
            return ResponseEntity.internalServerError().body("Match not found");
        }

        if (!match.isValidMatch()) {
            return ResponseEntity.ok("Match already finished");
        }

        matchService.finish(match);

        return ResponseEntity.ok("Match finished");
    }
}
