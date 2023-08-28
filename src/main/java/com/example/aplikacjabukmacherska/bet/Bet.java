package com.example.aplikacjabukmacherska.bet;

import com.example.aplikacjabukmacherska.match.MatchResult;
import com.example.aplikacjabukmacherska.match.Match;

import javax.persistence.*;

@Entity
public class Bet {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStake(Double stake) {
        this.stake = stake;
    }

    public void setPossibleWin(Double possibleWin) {
        this.possibleWin = possibleWin;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private MatchResult matchbet;
    private Double stake;

    private Double possibleWin;
    @ManyToOne
    private Match match;

    public MatchResult getMatchbet() {
        return matchbet;
    }

    public void setMatchbet(MatchResult matchbet) {
        this.matchbet = matchbet;
    }

    public Double getStake() {
        return stake;
    }

    public void setStake(double stake) {
        if (stake < 0) {
            throw new IllegalArgumentException("Stawka nie może być mniejsza niż 0.00 zł");
        }
        this.stake = stake;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Double calculatePossibleWin() {
        possibleWin = stake;
        if (matchbet.equals(MatchResult.TEAM_A)) {
            possibleWin *= match.getMatchDetails().getOddTeamA();
        } else if (matchbet.equals(MatchResult.TEAM_B)) {
            possibleWin *= match.getMatchDetails().getOddTeamB();
        } else {
            possibleWin *= match.getMatchDetails().getOddDraw();
        }
        return possibleWin;
    }

    public Double getPossibleWin() {
        return possibleWin;
    }
}
