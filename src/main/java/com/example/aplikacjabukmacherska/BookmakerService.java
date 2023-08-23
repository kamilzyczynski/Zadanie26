package com.example.aplikacjabukmacherska;

import com.example.aplikacjabukmacherska.bet.Bet;
import com.example.aplikacjabukmacherska.bet.BetRepository;
import com.example.aplikacjabukmacherska.match.Match;
import com.example.aplikacjabukmacherska.match.MatchRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class BookmakerService {
    private MatchRepository matchRepository;
    private BetRepository betRepository;

    public BookmakerService(MatchRepository matchRepository, BetRepository betRepository) {
        this.matchRepository = matchRepository;
        this.betRepository = betRepository;
    }

    public List<Match> showMatchesAvailableForBetting() {
        return matchRepository.findAllByBettingClosedIsFalse();
    }

    public Optional<Match> findMatchById(Long id) {
        return matchRepository.findById(id);
    }

    public void addNewMatchForBetting(Match match) {
        matchRepository.save(match);
    }

    public void save(Match match) {
        matchRepository.save(match);
    }

    public void save(Bet bet) {
        betRepository.save(bet);
    }

}
