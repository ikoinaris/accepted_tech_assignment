package com.ikoinaris.matchbet.service;

import com.ikoinaris.matchbet.entity.Match;
import com.ikoinaris.matchbet.exception.InvalidMatchIdException;
import com.ikoinaris.matchbet.repository.MatchOddsRepository;
import com.ikoinaris.matchbet.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;

    private final MatchOddsRepository matchOddsRepository;

    public List<Match> findAllMatches() {
        return matchRepository.findAll();
    }

    public Optional<Match> findMatchById(Integer matchId) {
        return matchRepository.findById(matchId);
    }

    public Match saveMatch(Match match) { return matchRepository.save(match); }

    public Match updateMatch(Integer matchId, Match updateMatch) {
        return matchRepository.findById(matchId)
                .map(existingMatch -> {
                    existingMatch.setDescription(updateMatch.getDescription());
                    existingMatch.setMatchDate(updateMatch.getMatchDate());
                    existingMatch.setMatchTime(updateMatch.getMatchTime());
                    existingMatch.setTeamA(updateMatch.getTeamA());
                    existingMatch.setTeamB(updateMatch.getTeamB());
                    existingMatch.setSport(updateMatch.getSport());
                    return matchRepository.save(existingMatch);
                }).orElseThrow(() -> new InvalidMatchIdException(matchId));
    }

    public void deleteMatch(Integer matchId) {
        if(!matchRepository.findById(matchId).isPresent()) {
            throw new InvalidMatchIdException(matchId);
        }
        if(!matchOddsRepository.findByMatchId(matchId).isEmpty()) {
            matchOddsRepository.deleteAllOddsByMatchId(matchId);
        }
        matchRepository.deleteById(matchId);
    }
}
