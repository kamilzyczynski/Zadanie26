package com.example.aplikacjabukmacherska;

import com.example.aplikacjabukmacherska.bet.Bet;
import com.example.aplikacjabukmacherska.bet.BetRepository;
import com.example.aplikacjabukmacherska.match.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookmakerService {
    private MatchRepository matchRepository;
    private BetRepository betRepository;
    private MatchDetailsRepository matchDetailsRepository;

    public BookmakerService(MatchRepository matchRepository, BetRepository betRepository, MatchDetailsRepository matchDetailsRepository) {
        this.matchRepository = matchRepository;
        this.betRepository = betRepository;
        this.matchDetailsRepository = matchDetailsRepository;
    }

    public List<Match> showMatchesAvailableForBetting() {
        return matchRepository.findAllByBettingClosedIsFalse();
    }

    public Optional<Match> findMatchById(Long id) {
        return matchRepository.findById(id);
    }

    public void addNewMatchForBetting(Match match) {
        MatchDetails matchDetails = new MatchDetails();
        matchDetails.setOddTeamA(match.getMatchDetails().getOddTeamA());
        matchDetails.setOddTeamB(match.getMatchDetails().getOddTeamB());
        matchDetails.setOddDraw(match.getMatchDetails().getOddDraw());

        matchDetailsRepository.save(matchDetails);
        match.setMatchDetails(matchDetails);

        matchRepository.save(match);
    }

    public void save(Match match) {
        matchRepository.save(match);
    }

    public void save(Bet bet) {
        betRepository.save(bet);
    }

    public void deleteMatch(Match match) {
        matchRepository.delete(match);
    }

    public List<Bet> findAll() {
        return betRepository.findAll();
    }

    public List<Match> findTop3ByBets() {
        return matchRepository.findTop3ByOrderByBetsDesc();
    }

    public Optional<Bet> findBetById(Long id) {
        return betRepository.findById(id);
    }

    public Long makeBet(Bet bet, Long matchId) {
        Optional<Match> matchById = findMatchById(matchId);

        if (matchById.isPresent()) {
            Match match = matchById.get();
            bet.setMatch(match);
            bet.calculatePossibleWin();
            save(bet);
            return bet.getId();
        }
        return null;
    }

    public void addMatchScore(String result, Long matchId) {
        Optional<Match> matchById = findMatchById(matchId);

        if (matchById.isPresent()) {
            Match matchFromDb = matchById.get();
            MatchResult matchResult = MatchResult.valueOf(result);
            matchFromDb.setResult(matchResult);
            matchFromDb.setBettingClosed(true);
            save(matchFromDb);
        }
    }
}
