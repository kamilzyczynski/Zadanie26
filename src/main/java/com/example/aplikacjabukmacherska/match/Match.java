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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private boolean bettingClosed;

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
}
