package com.ikoinaris.matchbet.controller;

import com.ikoinaris.matchbet.TestSampleFields;
import com.ikoinaris.matchbet.entity.MatchOdds;
import com.ikoinaris.matchbet.service.MatchOddsService;
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
class MatchOddsControllerTest {

    @Mock
    private MatchOddsService matchOddsService;

    @InjectMocks
    private MatchOddsController matchOddsController;

    @Test
    void givenListOfMatchOdds_whenFindAllMatchOddsCalled_thenServiceHandlesRequest() {
        // Given
        List<MatchOdds> matchOdds = Arrays.asList(new TestSampleFields().createSampleMatchOdd());
        when(matchOddsService.findAllOdds()).thenReturn(matchOdds);

        // When
        List<MatchOdds> result = matchOddsController.findAllMatchOdds();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(matchOddsService, times(1)).findAllOdds();
    }

    @Test
    void givenMatchOdd_whenFindMatchOddByIdCalled_thenServiceHandlesRequest() {
        // Given
        MatchOdds matchOdds = new TestSampleFields().createSampleMatchOdd();
        when(matchOddsService.findMatchOddById(1)).thenReturn(Optional.of(matchOdds));

        // When
        ResponseEntity<MatchOdds> response = matchOddsController.findMatchOddById(1);

        // Then
        assertNotNull(response);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(matchOdds, response.getBody());
        verify(matchOddsService, times(1)).findMatchOddById(1);
    }

    @Test
    void givenMatchOdd_whenFindOddsByMatchIdCalled_thenServiceHandlesRequest() {
        // Arrange
        List<MatchOdds> odds = Arrays.asList(new TestSampleFields().createSampleMatchOdd());
        when(matchOddsService.findAllOddsByMatchId(1)).thenReturn(odds);

        // Act
        List<MatchOdds> result = matchOddsController.findOddsByMatchId(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(matchOddsService, times(1)).findAllOddsByMatchId(1);
    }

    @Test
    void givenMatchOdd_whenCreateMatchOddCalled_thenServiceHandlesRequest() {
        // Given
        MatchOdds matchOdd = new TestSampleFields().createSampleMatchOdd();
        when(matchOddsService.saveMatchOdd(matchOdd, 1)).thenReturn(matchOdd);

        // When
        MatchOdds result = matchOddsController.createMatchOdd(matchOdd, 1);

        // Then
        assertNotNull(result);
        assertEquals(matchOdd, result);
        verify(matchOddsService, times(1)).saveMatchOdd(matchOdd, 1);
    }

    @Test
    void givenMatchOdd_whenUpdateMatchOddCalled_thenServiceHandlesRequest() {
        // Given
        MatchOdds matchOdds = new TestSampleFields().createSampleMatchOdd();
        when(matchOddsService.updateMatchOdd(eq(1), any(MatchOdds.class))).thenReturn(matchOdds);

        // When
        MatchOdds result = matchOddsController.updateMatchOdd(1, matchOdds);

        // Then
        assertNotNull(result);
        assertEquals(matchOdds, result);
        verify(matchOddsService, times(1)).updateMatchOdd(1, matchOdds);
    }

    @Test
    void whenDeleteMatchOddCalled_thenServiceHandlesRequest() {
        // When
        ResponseEntity<Void> response = matchOddsController.deleteMatchOdd(1);

        // Then
        assertNotNull(response);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        verify(matchOddsService, times(1)).deleteMatchOdd(1);
    }
}