package com.ikoinaris.matchbet.integration;

import com.ikoinaris.matchbet.entity.Match;
import com.ikoinaris.matchbet.entity.MatchOdds;
import com.ikoinaris.matchbet.model.Sport;
import com.ikoinaris.matchbet.repository.MatchOddsRepository;
import com.ikoinaris.matchbet.repository.MatchRepository;
import com.ikoinaris.matchbet.service.MatchOddsService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class MatchOddsIntegrationTest {

    @Autowired
    private MatchOddsService matchOddsService;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private MatchOddsRepository matchOddsRepository;

    @Test
    public void givenNewMatchOdds_whenFindAllOddsByMatchIdCalled_thenSuccessfullyReturnMatchOdds() {

        // Given
        Match match = new Match();
        match.setDescription("Olympiakos - Panathinaikos");
        match.setMatchDate(LocalDate.now());
        match.setMatchTime(LocalTime.now());
        match.setTeamA("Olympiakos");
        match.setTeamB("Panathinaikos");
        match.setSport(Sport.FOOTBALL);

        MatchOdds oddOne = new MatchOdds();
        oddOne.setMatch(match);
        oddOne.setSpecifier("1");
        oddOne.setOdd(3.5);

        MatchOdds oddTwo = new MatchOdds();
        oddTwo.setMatch(match);
        oddTwo.setSpecifier("2");
        oddTwo.setOdd(2.5);

        matchRepository.save(match);
        matchOddsRepository.save(oddOne);
        matchOddsRepository.save(oddTwo);

        // When
        List<MatchOdds> odds = matchOddsService.findAllOddsByMatchId(match.getId());

        // Then
        assertEquals(2, odds.size());
        assertEquals("1", odds.get(0).getSpecifier());
        assertEquals("2", odds.get(1).getSpecifier());
    }

    @Test
    public void givenSavedMatchOdds_whenUpdateMatchOddCalled_thenSuccessfullyUpdateMatchOdd() {

        // Given
        Match match = new Match();
        match.setDescription("Olympiakos - Panathinaikos");
        match.setMatchDate(LocalDate.now());
        match.setMatchTime(LocalTime.now());
        match.setTeamA("Olympiakos");
        match.setTeamB("Panathinaikos");
        match.setSport(Sport.FOOTBALL);

        MatchOdds oddOne = new MatchOdds();
        oddOne.setMatch(match);
        oddOne.setSpecifier("1");
        oddOne.setOdd(3.5);

        MatchOdds oddTwo = new MatchOdds();
        oddTwo.setMatch(match);
        oddTwo.setSpecifier("2");
        oddTwo.setOdd(2.5);

        matchRepository.save(match);
        matchOddsRepository.save(oddOne);
        matchOddsRepository.save(oddTwo);

        MatchOdds updateMatchOddTwo = new MatchOdds();
        updateMatchOddTwo.setSpecifier("2");
        updateMatchOddTwo.setOdd(3.5);

        // When
        MatchOdds updatedMatchOdd = matchOddsService.updateMatchOdd(oddTwo.getId(), updateMatchOddTwo);

        // Then
        assertEquals(oddTwo.getOdd(), 3.5);
    }

    @Test
    public void givenSavedMatchOdds_whenDeleteMatchOddCalled_thenSuccessfullyDeleteMatchOdd() {

        // Given
        Match match = new Match();
        match.setDescription("Olympiakos - Panathinaikos");
        match.setMatchDate(LocalDate.now());
        match.setMatchTime(LocalTime.now());
        match.setTeamA("Olympiakos");
        match.setTeamB("Panathinaikos");
        match.setSport(Sport.FOOTBALL);

        MatchOdds oddOne = new MatchOdds();
        oddOne.setMatch(match);
        oddOne.setSpecifier("1");
        oddOne.setOdd(3.5);

        MatchOdds oddTwo = new MatchOdds();
        oddTwo.setMatch(match);
        oddTwo.setSpecifier("2");
        oddTwo.setOdd(2.5);

        matchRepository.save(match);
        matchOddsRepository.save(oddOne);
        matchOddsRepository.save(oddTwo);

        // When
        matchOddsRepository.deleteById(oddTwo.getId());
        Optional<MatchOdds> odds = matchOddsRepository.findById(oddTwo.getId());

        // Then
        assertTrue(!odds.isPresent());

    }
}
