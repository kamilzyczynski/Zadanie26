package com.example.aplikacjabukmacherska.bet;

import com.example.aplikacjabukmacherska.MatchResult;
import com.example.aplikacjabukmacherska.match.Match;

import javax.persistence.*;

@Entity
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private MatchResult matchbet;
    private double stake;
    @ManyToOne
    private Match match;

    public MatchResult getMatchbet() {
        return matchbet;
    }

    public void setMatchbet(MatchResult matchbet) {
        this.matchbet = matchbet;
    }

    public double getStake() {
        return stake;
    }

    public void setStake(double stake) {
        this.stake = stake;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
