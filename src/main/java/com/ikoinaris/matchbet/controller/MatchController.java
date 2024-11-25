package com.ikoinaris.matchbet.controller;

import com.ikoinaris.matchbet.entity.Match;
import com.ikoinaris.matchbet.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
@Tag(name = "Match API", description = "Endpoint for managing a match")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @Operation(summary = "Get all matches", description = "Retrieve all saved matches")
    @GetMapping
    public List<Match> findAllMatches() {
        return matchService.findAllMatches();
    }

    @Operation(summary = "Get a specific match", description = "Retrieve a match based on its Id")
    @GetMapping("/{id}")
    public ResponseEntity<Match> findMatchById(
            @Parameter(description = "Id of the Match to be retrieved") @PathVariable Integer id) {
        return matchService.findMatchById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new match", description = "Add a new match")
    @PostMapping
    public Match createMatch(
            @Parameter(description = "Match object to be created") @RequestBody Match match) {
        return matchService.saveMatch(match);
    }

    @Operation(summary = "Update a match ", description = "Update an existing match")
    @PutMapping("/{id}")
    public Match updateMatch(
            @Parameter(description = "Id of Match to be updated") @PathVariable Integer id,
            @Parameter(description = "Match object to be updated") @RequestBody Match match) {
        return matchService.updateMatch(id, match);
    }

    @Operation(summary = "Delete a match", description = "Delete a specific match based on its Id.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(
            @Parameter(description = "Id of the Match to be deleted") @PathVariable Integer id) {
        matchService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }
}
