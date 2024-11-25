package com.ikoinaris.matchbet.controller;

import com.ikoinaris.matchbet.TestSampleFields;
import com.ikoinaris.matchbet.entity.Match;
import com.ikoinaris.matchbet.service.MatchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatchControllerTest {

    @Mock
    private MatchService matchService;

    @InjectMocks
    private MatchController matchController;

    @Test
    void givenListOfMatches_whenFindAllMatchesCalled_thenServiceHandlesRequest() {
        // Given
        List<Match> matches = Arrays.asList(new TestSampleFields().createSampleMatch());
        when(matchService.findAllMatches()).thenReturn(matches);

        // When
        List<Match> result = matchController.findAllMatches();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(matchService, times(1)).findAllMatches();
    }

    @Test
    void givenMatch_whenFindMatchByIdCalled_thenServiceHandlesRequest() {
        // Given
        Match match = new TestSampleFields().createSampleMatch();
        when(matchService.findMatchById(1)).thenReturn(Optional.of(match));

        // When
        ResponseEntity<Match> response = matchController.findMatchById(1);

        // Then
        assertNotNull(response);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(match, response.getBody());
        verify(matchService, times(1)).findMatchById(1);
    }

    @Test
    void givenMatch_whenCreateMatchCalled_thenServiceHandlesRequest() {
        // Given
        Match match = new TestSampleFields().createSampleMatch();
        when(matchService.saveMatch(match)).thenReturn(match);

        // When
        Match result = matchController.createMatch(match);

        // Then
        assertNotNull(result);
        assertEquals(match, result);
        verify(matchService, times(1)).saveMatch(match);
    }

    @Test
    void givenMatch_whenUpdateMatchCalled_thenServiceHandlesRequest() {
        // Given
        Match match = new TestSampleFields().createSampleMatch();
        when(matchService.updateMatch(eq(1), any(Match.class))).thenReturn(match);

        // When
        Match result = matchController.updateMatch(1, match);

        // Then
        assertNotNull(result);
        assertEquals(match, result);
        verify(matchService, times(1)).updateMatch(1, match);
    }

    @Test
    void whenDeleteMatchCalled_thenServiceHandlesRequest() {
        // When
        ResponseEntity<Void> response = matchController.deleteMatch(1);

        // Then
        assertNotNull(response);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        verify(matchService, times(1)).deleteMatch(1);
    }
}