package com.ikoinaris.matchbet.controller;

import com.ikoinaris.matchbet.entity.MatchOdds;
import com.ikoinaris.matchbet.service.MatchOddsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match-odds")
@Tag(name = "MatchOdds API", description = "Endpoints for managing match odds")
@RequiredArgsConstructor
public class MatchOddsController {

    private final MatchOddsService matchOddsService;

    @Operation(summary = "Get all odds", description = "Retrieve all odds for all saved matches")
    @GetMapping
    public List<MatchOdds> findAllMatchOdds() { return matchOddsService.findAllOdds(); }

    @Operation(summary = "Get a specific match odd", description = "Retrieve a match odd based on its Id")
    @GetMapping("/{id}")
    public ResponseEntity<MatchOdds> findMatchOddById(
            @Parameter(description = "Id of the Match Odd to be retrieved") @PathVariable Integer id) {
        return matchOddsService.findMatchOddById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all odds for a specific match", description = "Retrieve all odds associated with a given match Id.")
    @GetMapping("/{matchId}/odds")
    public List<MatchOdds> findOddsByMatchId(
            @Parameter(description = "Id of the match to retrieve odds for") @PathVariable Integer matchId) {
        return matchOddsService.findAllOddsByMatchId(matchId);
    }

    @Operation(summary = "Create a new match odd", description = "Add a new odd for a specific match.")
    @PostMapping("/{matchId}/odds")
    public MatchOdds createMatchOdd(
            @Parameter(description = "MatchOdd object to be created") @RequestBody MatchOdds matchOdd,
            @Parameter(description = "Id of the match to associate the odd with") @PathVariable Integer matchId) {
        return matchOddsService.saveMatchOdd(matchOdd, matchId);
    }


    @Operation(summary = "Update a match odd", description = "Update an existing match odd")
    @PutMapping("/{id}")
    public MatchOdds updateMatchOdd(
            @Parameter(description = "Id of MatchOdd to be updated") @PathVariable Integer id,
            @Parameter(description = "MatchOdd object to be updated") @RequestBody MatchOdds matchOdds) {
        return matchOddsService.updateMatchOdd(id, matchOdds);
    }

    @Operation(summary = "Delete a match odd", description = "Delete a specific odd by its Id.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatchOdd(
            @Parameter(description = "Id of the MatchOdd to be deleted") @PathVariable Integer id) {
        matchOddsService.deleteMatchOdd(id);
        return ResponseEntity.noContent().build();
    }
}
