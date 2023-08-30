package com.example.aplikacjabukmacherska.bet;

import com.example.aplikacjabukmacherska.match.MatchResult;
import com.example.aplikacjabukmacherska.match.Match;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private MatchResult matchbet;
    private Double stake;

    private Double possibleWin;
    @ManyToOne (cascade = CascadeType.REMOVE)
    private Match match;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPossibleWin(Double possibleWin) {
        this.possibleWin = possibleWin;
    }

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

        BigDecimal possibleWinBigDecimal = new BigDecimal(possibleWin);
        possibleWinBigDecimal = possibleWinBigDecimal.setScale(2, RoundingMode.HALF_UP);

        return possibleWinBigDecimal.doubleValue();
    }

    public Double getPossibleWin() {
        return possibleWin;
    }
}
