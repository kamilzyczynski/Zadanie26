package com.example.aplikacjabukmacherska.match;

import javax.persistence.*;

@Entity
public class MatchDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer scoreTeamA;
    private Integer scoreTeamB;
    private Double oddTeamA;
    private Double oddDraw;
    private Double oddTeamB;

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
}
