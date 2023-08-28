package com.example.aplikacjabukmacherska.match;

import com.example.aplikacjabukmacherska.match.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findAllByBettingClosedIsFalse();

    @Query(value = "SELECT m.* FROM match m LEFT JOIN bet b ON m.id = b.match_id WHERE (m.betting_closed = false)  GROUP BY m.id ORDER BY COUNT(b.id) DESC LIMIT 3", nativeQuery = true)
    List<Match> findTop3ByOrderByBetsDesc();
}
