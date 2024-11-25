package com.ikoinaris.matchbet.repository;

import com.ikoinaris.matchbet.entity.MatchOdds;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchOddsRepository extends JpaRepository<MatchOdds, Integer> {

    @Query("SELECT mo FROM MatchOdds mo WHERE mo.match.id = :matchId")
    List<MatchOdds> findByMatchId(Integer matchId);

    @Modifying
    @Transactional
    @Query("DELETE FROM MatchOdds mo WHERE mo.match.id = :matchId")
    void deleteAllOddsByMatchId(Integer matchId);
}
