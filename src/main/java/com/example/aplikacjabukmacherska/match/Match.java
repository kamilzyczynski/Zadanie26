package com.example.aplikacjabukmacherska.match;

import com.example.aplikacjabukmacherska.bet.Bet;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String teamA;
    private String teamB;
    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private MatchResult result;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private boolean bettingClosed;

    @OneToMany(mappedBy = "match", cascade = {CascadeType.REMOVE})
    private List<Bet> bets;

    @OneToOne
    @JoinColumn(name = "match_details_id", unique = true)
    private MatchDetails matchDetails;

    public MatchDetails getMatchDetails() {
        return matchDetails;
    }

    public void setMatchDetails(MatchDetails matchDetails) {
        this.matchDetails = matchDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public MatchResult getResult() {
        return result;
    }

    public void setResult(MatchResult result) {
        this.result = result;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isBettingClosed() {
        return bettingClosed;
    }

    public void setBettingClosed(boolean bettingClosed) {
        this.bettingClosed = bettingClosed;
    }

    public void setResultWhenScoreIsKnown() {
        if (matchDetails.getScoreTeamA() > matchDetails.getScoreTeamB()) {
            setResult(MatchResult.TEAM_A);
        } else if (matchDetails.getScoreTeamA() < matchDetails.getScoreTeamB()) {
            setResult(MatchResult.TEAM_B);
        } else {
            setResult(MatchResult.DRAW);
        }
    }
}
