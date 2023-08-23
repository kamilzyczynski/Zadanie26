package com.example.aplikacjabukmacherska.match;

import com.example.aplikacjabukmacherska.MatchResult;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String teamA;
    private String teamB;

    private Double oddTeamA;
    private Double oddDraw;
    private Double oddTeamB;
    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private MatchResult result;
    private Integer scoreTeamA;
    private Integer scoreTeamB;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private boolean bettingClosed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScoreTeamA() {
        return scoreTeamA;
    }

    public void setScoreTeamA(Integer scoreTeamA) {
        if (scoreTeamA < 0) {
            throw new IllegalArgumentException("Wynik nie może być mniejszy niż 0");
        }
        this.scoreTeamA = scoreTeamA;
    }

    public Integer getScoreTeamB() {
        return scoreTeamB;
    }

    public void setScoreTeamB(Integer scoreTeamB) {
        if (scoreTeamB < 0) {
            throw new IllegalArgumentException("Wynik nie może być mniejszy niż 0");
        }
        this.scoreTeamB = scoreTeamB;
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

    public Double getOddTeamA() {
        return oddTeamA;
    }

    public void setOddTeamA(Double oddTeamA) {
        if (oddTeamA < 1) {
            throw new IllegalArgumentException("Kurs nie może być mniejszy niż 1");
        }
        this.oddTeamA = oddTeamA;
    }

    public Double getOddDraw() {
        return oddDraw;
    }

    public void setOddDraw(Double oddDraw) {
        if (oddDraw < 1) {
            throw new IllegalArgumentException("Kurs nie może być mniejszy niż 1");
        }
        this.oddDraw = oddDraw;
    }

    public Double getOddTeamB() {
        return oddTeamB;
    }

    public void setOddTeamB(Double oddTeamB) {
        if (oddTeamB < 1) {
            throw new IllegalArgumentException("Kurs nie może być mniejszy niż 1");
        }
        this.oddTeamB = oddTeamB;
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
        if (scoreTeamA > scoreTeamB) {
            setResult(MatchResult.TEAM_A);
        } else if (scoreTeamA < scoreTeamB) {
            setResult(MatchResult.TEAM_B);
        } else {
            setResult(MatchResult.DRAW);
        }
    }
}
