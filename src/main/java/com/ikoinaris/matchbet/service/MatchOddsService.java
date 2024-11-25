package com.ikoinaris.matchbet.service;

import com.ikoinaris.matchbet.entity.Match;
import com.ikoinaris.matchbet.entity.MatchOdds;
import com.ikoinaris.matchbet.exception.InvalidMatchIdException;
import com.ikoinaris.matchbet.exception.InvalidMatchOddIdException;
import com.ikoinaris.matchbet.exception.InvalidSpecifierException;
import com.ikoinaris.matchbet.repository.MatchOddsRepository;
import com.ikoinaris.matchbet.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchOddsService {

    private final MatchOddsRepository matchOddsRepository;

    private final MatchRepository matchRepository;

    public List<MatchOdds> findAllOdds() { return matchOddsRepository.findAll(); }

    public Optional<MatchOdds> findMatchOddById(Integer matchOddId) {
        return matchOddsRepository.findById(matchOddId);
    }

    public List<MatchOdds> findAllOddsByMatchId(Integer matchId) {
        if(!matchRepository.findById(matchId).isPresent()) {
            throw new InvalidMatchIdException(matchId);
        }
        return matchOddsRepository.findByMatchId(matchId);
    }

    public MatchOdds saveMatchOdd(MatchOdds matchOdd, int matchId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new InvalidMatchIdException(matchId));
        matchOdd.setMatch(match);
        if(!Arrays.asList("X", "1", "2").contains(matchOdd.getSpecifier())) {
            throw new InvalidSpecifierException();
        }
        return matchOddsRepository.save(matchOdd);
    }

    public MatchOdds updateMatchOdd(int matchOddId, MatchOdds updateMatchOdd) {
        return matchOddsRepository.findById(matchOddId)
                .map(existingMatchOdds -> {
                    existingMatchOdds.setOdd(updateMatchOdd.getOdd());
                    existingMatchOdds.setSpecifier(updateMatchOdd.getSpecifier());
                    return matchOddsRepository.save(existingMatchOdds);
                }).orElseThrow(() -> new InvalidMatchOddIdException(matchOddId));
    }

    public void deleteMatchOdd(int matchOddId) {
        if (!matchOddsRepository.findById(matchOddId).isPresent()) {
            throw new InvalidMatchOddIdException(matchOddId);
        }
        matchOddsRepository.deleteById(matchOddId);
    }
}
