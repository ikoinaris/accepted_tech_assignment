package com.ikoinaris.matchbet.service;

import com.ikoinaris.matchbet.TestSampleFields;
import com.ikoinaris.matchbet.entity.Match;
import com.ikoinaris.matchbet.exception.InvalidMatchIdException;
import com.ikoinaris.matchbet.repository.MatchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatchServiceTest {

    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    private MatchService matchService;

    @Test
    void givenListOfMatches_whenFindAllMatchesCalled_thenCallInRepositoryVerified() {
        // Given
        List<Match> matches = List.of(new Match(), new Match());
        when(matchRepository.findAll()).thenReturn(matches);

        // When
        List<Match> result = matchService.findAllMatches();

        // Then
        assertEquals(2, result.size());
        verify(matchRepository, times(1)).findAll();
    }

    @Test
    void givenMatch_whenFindMatchByIdCalled_thenMatchSuccessfullyReturned() {
        // Given
        Match match = new TestSampleFields().createSampleMatch();
        when(matchRepository.findById(1)).thenReturn(Optional.of(match));

        // When
        Optional<Match> result = matchService.findMatchById(1);

        // Then
        assertTrue(result.isPresent());
        assertEquals(match, result.get());
        verify(matchRepository, times(1)).findById(1);
    }

    @Test
    void givenMatch_whenSaveMatchCalled_thenSuccessfullyHandled() {
        // Given
        Match match = new TestSampleFields().createSampleMatch();
        when(matchRepository.save(match)).thenReturn(match);

        // When
        Match result = matchService.saveMatch(match);

        // Then
        assertNotNull(result);
        assertEquals(match, result);
        verify(matchRepository, times(1)).save(match);
    }

    @Test
    void givenMatch_whenUpdateMatchCalled_thenSuccessfullyUpdated() {
        // Given
        Match existingMatch = new TestSampleFields().createSampleMatch();

        Match updatedMatch = new TestSampleFields().createSampleMatch();
        updatedMatch.setDescription("Olympiakos - Panathinaikos");

        when(matchRepository.findById(1)).thenReturn(Optional.of(existingMatch));
        when(matchRepository.save(any(Match.class))).thenReturn(existingMatch);

        // When
        Match result = matchService.updateMatch(1, updatedMatch);

        // Then
        assertNotNull(result);
        assertEquals("Olympiakos - Panathinaikos", result.getDescription());
        verify(matchRepository, times(1)).findById(1);
        verify(matchRepository, times(1)).save(existingMatch);
    }

    @Test
    void givenInvalidMatchId_whenDeleteMatchCalled_thenInvalidMatchIdExceptionThrown() {

        assertThrows(InvalidMatchIdException.class, () -> matchService.deleteMatch(1));
    }
}