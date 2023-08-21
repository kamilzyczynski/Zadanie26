package com.example.aplikacjabukmacherska.match;

import com.example.aplikacjabukmacherska.match.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findAllByBettingClosedIsFalse();
}
