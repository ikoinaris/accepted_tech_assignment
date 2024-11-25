package com.ikoinaris.matchbet;

import com.ikoinaris.matchbet.entity.Match;
import com.ikoinaris.matchbet.entity.MatchOdds;
import com.ikoinaris.matchbet.model.Sport;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TestSampleFields {

    public Match createSampleMatch() {
        Match match = new Match();
        match.setId(1);
        match.setDescription("OSFP-PAO");
        match.setMatchDate(LocalDate.of(2024, 11, 20));
        match.setMatchTime(LocalTime.of(20, 0));
        match.setTeamA("OSFP");
        match.setTeamB("PAO");
        match.setSport(Sport.FOOTBALL);
        return match;
    }

    public MatchOdds createSampleMatchOdd() {
        MatchOdds matchOdd = new MatchOdds();
        matchOdd.setId(1);
        matchOdd.setSpecifier("1");
        matchOdd.setOdd(1.5);
        return matchOdd;
    }

    public List<MatchOdds> createSampleMatchOddsList() {

        MatchOdds matchOddOne = new MatchOdds();
        matchOddOne.setId(1);
        matchOddOne.setSpecifier("1");
        matchOddOne.setOdd(1.5);

        MatchOdds matchOddTwo = new MatchOdds();
        matchOddTwo.setId(2);
        matchOddTwo.setSpecifier("2");
        matchOddTwo.setOdd(2.5);

        return List.of(matchOddOne, matchOddTwo);
    }
}
