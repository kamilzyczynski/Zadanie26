package com.example.aplikacjabukmacherska.match;

import jakarta.persistence.*;

@Entity
public class MatchDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double oddTeamA;
    private Double oddDraw;
    private Double oddTeamB;

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
