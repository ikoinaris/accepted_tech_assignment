package com.ikoinaris.matchbet.integration;

import com.ikoinaris.matchbet.entity.Match;
import com.ikoinaris.matchbet.model.Sport;
import com.ikoinaris.matchbet.repository.MatchRepository;
import com.ikoinaris.matchbet.service.MatchService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class MatchIntegrationTest {

    @Autowired
    private MatchService matchService;

    @Autowired
    private MatchRepository matchRepository;

    @Test
    public void givenNewMatch_whenMatchSaved_thenFindMatchById() {
        // Given
        Match match = new Match();
        match.setDescription("Olympiakos - Panathinaikos");
        match.setMatchDate(LocalDate.now());
        match.setMatchTime(LocalTime.now());
        match.setTeamA("Olympiakos");
        match.setTeamB("Panathinaikos");
        match.setSport(Sport.FOOTBALL);

        // When
        Match savedMatch = matchRepository.save(match);

        Optional<Match> retrievedMatch = matchService.findMatchById(savedMatch.getId());

        // Then
        assertTrue(retrievedMatch.isPresent());
        assertEquals("Olympiakos - Panathinaikos", retrievedMatch.get().getDescription());
    }

    @Test
    public void givenSavedMatch_whenDeleteMatchCalled_thenSuccessfullyDeleteMatch() {
        // Given
        Match match = new Match();
        match.setDescription("Olympiakos - Panathinaikos");
        match.setMatchDate(LocalDate.now());
        match.setMatchTime(LocalTime.now());
        match.setTeamA("Olympiakos");
        match.setTeamB("Panathinaikos");
        match.setSport(Sport.FOOTBALL);

        // When
        Match savedMatch = matchRepository.save(match);

        matchService.deleteMatch(savedMatch.getId());

        // Then
        Optional<Match> retrievedMatch = matchService.findMatchById(savedMatch.getId());
        assertFalse(retrievedMatch.isPresent());
    }

    @Test
    public void givenSavedMatch_whenUpdateMatchCalled_thenSuccessfullyUpdateMatch() {
        // Given
        Match match = new Match();
        match.setDescription("Olympiakos - Panathinaikos");
        match.setMatchDate(LocalDate.now());
        match.setMatchTime(LocalTime.now());
        match.setTeamA("Olympiakos");
        match.setTeamB("Panathinaikos");
        match.setSport(Sport.FOOTBALL);

        // When
        Match savedMatch = matchRepository.save(match);
        Optional<Match> retrievedMatch = matchService.findMatchById(savedMatch.getId());

        Match newMatch = new Match();
        newMatch.setDescription("Olympiakos - Panathinaikos");
        newMatch.setMatchDate(LocalDate.now());
        newMatch.setMatchTime(LocalTime.now());
        newMatch.setTeamA("Olympiakos");
        newMatch.setTeamB("Panathinaikos");
        newMatch.setSport(Sport.BASKETBALL);

        Match updatedMatch = matchService.updateMatch(retrievedMatch.get().getId(), newMatch);
        Optional<Match> retrievedUpdatedMatch = matchService.findMatchById(updatedMatch.getId());

        // Then
        assertTrue(retrievedUpdatedMatch.isPresent());
        assertEquals("Olympiakos - Panathinaikos", retrievedUpdatedMatch.get().getDescription());
        assertEquals(Sport.BASKETBALL, retrievedUpdatedMatch.get().getSport());
    }
}
