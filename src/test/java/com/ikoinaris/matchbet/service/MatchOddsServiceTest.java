package com.ikoinaris.matchbet.service;

import com.ikoinaris.matchbet.TestSampleFields;
import com.ikoinaris.matchbet.entity.Match;
import com.ikoinaris.matchbet.entity.MatchOdds;
import com.ikoinaris.matchbet.exception.InvalidMatchIdException;
import com.ikoinaris.matchbet.exception.InvalidSpecifierException;
import com.ikoinaris.matchbet.repository.MatchOddsRepository;
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
class MatchOddsServiceTest {

    @Mock
    private MatchOddsRepository matchOddsRepository;

    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    private MatchOddsService matchOddsService;

    @Test
    void givenSavedMatchOdds_whenFindAllOddsCalled_thenSavedMatchOddsReturned() {
        // Given
        List<MatchOdds> matchOddsList = new TestSampleFields().createSampleMatchOddsList();
        when(matchOddsRepository.findAll()).thenReturn(matchOddsList);

        // When
        List<MatchOdds> result = matchOddsService.findAllOdds();

        // Then
        assertEquals(2, result.size());
    }

    @Test
    void givenMatchOdd_whenFindMatchOddByIdCalled_thenMatchOddSuccessfullyReturned() {
        // Given
        MatchOdds matchOdds = new TestSampleFields().createSampleMatchOdd();
        when(matchOddsRepository.findById(1)).thenReturn(Optional.of(matchOdds));

        // When
        Optional<MatchOdds> result = matchOddsService.findMatchOddById(1);

        // Then
        assertTrue(result.isPresent());
        assertEquals(matchOdds, result.get());
        verify(matchOddsRepository, times(1)).findById(1);
    }

    @Test
    void givenListOfMatchOdds_whenFindAllOddsByMatchIdCalled_thenCallInRepositoryVerified() {
        // Given
        MatchOdds matchOdds = new TestSampleFields().createSampleMatchOdd();
        Match match = new TestSampleFields().createSampleMatch();
        matchOdds.setMatch(match);
        List<MatchOdds> odds = List.of(matchOdds);
        when(matchRepository.findById(match.getId())).thenReturn(Optional.of(match));
        when(matchOddsRepository.findByMatchId(match.getId())).thenReturn(odds);

        // When
        List<MatchOdds> result = matchOddsService.findAllOddsByMatchId(match.getId());

        // Then
        assertEquals(1, result.size());
        verify(matchOddsRepository, times(1)).findByMatchId(1);
    }

    @Test
    void givenInvalidMatchId_whenFindAllOddsByMatchIdCalled_thenInvalidMatchIdExceptionThrown() {
        // Given
        Integer matchId = 99;
        when(matchRepository.findById(matchId)).thenReturn(Optional.empty());

        // When
        assertThrows(InvalidMatchIdException.class, () -> { matchOddsService.findAllOddsByMatchId(matchId); });

        // Then
        verify(matchRepository, times(1)).findById(matchId);
        verify(matchOddsRepository, never()).findByMatchId(any());
    }

    @Test
    void givenValidMatchId_whenSaveMatchOddCalled_thenSuccessfullyHandled() {
        // Given
        Match match = new TestSampleFields().createSampleMatch();
        MatchOdds matchOdds = new TestSampleFields().createSampleMatchOdd();
        when(matchOddsRepository.save(matchOdds)).thenReturn(matchOdds);
        when(matchRepository.findById(1)).thenReturn(Optional.of(match));

        // When
        MatchOdds result = matchOddsService.saveMatchOdd(matchOdds, match.getId());

        // Then
        assertNotNull(result);
        assertEquals(matchOdds, result);
        verify(matchOddsRepository, times(1)).save(matchOdds);
    }

    @Test
    void givenInvalidMatchId_whenSaveMatchOddCalled_thenInvalidMatchIdExceptionThrown() {
        // Given
        MatchOdds matchOdds = new MatchOdds();
        matchOdds.setSpecifier("1");
        when(matchRepository.findById(1)).thenReturn(Optional.empty());

        // When
        assertThrows(InvalidMatchIdException.class, () -> matchOddsService.saveMatchOdd(matchOdds, 1));

        // Then
        verify(matchRepository, times(1)).findById(1);
        verifyNoInteractions(matchOddsRepository);
    }

    @Test
    void givenInvalidSpecifier_whenSaveMatchOddCalled_thenInvalidSpecifierExceptionThrown() {
        // Given
        Match match = new Match();
        match.setId(1);
        MatchOdds matchOdds = new MatchOdds();
        matchOdds.setSpecifier("3");
        when(matchRepository.findById(1)).thenReturn(Optional.of(match));

        // When
        assertThrows(InvalidSpecifierException.class, () -> matchOddsService.saveMatchOdd(matchOdds, 1));

        // Then
        verify(matchRepository, times(1)).findById(1);
        verifyNoInteractions(matchOddsRepository);
    }
}