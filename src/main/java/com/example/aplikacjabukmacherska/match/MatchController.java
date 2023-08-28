package com.example.aplikacjabukmacherska.match;

import com.example.aplikacjabukmacherska.BookmakerService;
import com.example.aplikacjabukmacherska.bet.Bet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class MatchController {
    private BookmakerService bookmakerService;

    public MatchController(BookmakerService bookmakerService) {
        this.bookmakerService = bookmakerService;
    }

    @GetMapping("/match/add")
    public String showFormToAddNewMatch(Model model) {
        model.addAttribute("match", new Match());
        return "addNewMatch";
    }

    @PostMapping("/match/add")
    public String addNewMatch(Match match) {
        bookmakerService.addNewMatchForBetting(match);
        return "redirect:/";
    }

    @GetMapping("/match/bet/{id}")
    public String showFormToBetMatch(@PathVariable Long id, Model model) {
        Optional<Match> matchById = bookmakerService.findMatchById(id);

        if (matchById.isPresent()) {
            Match match = matchById.get();
            model.addAttribute("match", match);
            model.addAttribute("bet", new Bet());
            return "betMatch";
        }
        return "redirect:/";
    }

    @GetMapping("/match/delete/{id}")
    public String deleteMatch(@PathVariable Long id) {
        Optional<Match> matchById = bookmakerService.findMatchById(id);

        if (matchById.isPresent()) {
            Match match = matchById.get();
            bookmakerService.deleteMatch(match);
        }
        return "redirect:/";
    }

    @GetMapping("/bet/archive")
    public String showAllBets(Model model) {
        List<Bet> bets = bookmakerService.findAll();
        model.addAttribute("bets", bets);

        return "betsArchive";
    }

    @PostMapping("/match/bet")
    public String betMatch(Bet bet, @RequestParam Long matchId, Model model) {
        Optional<Match> matchById = bookmakerService.findMatchById(matchId);

        if (matchById.isPresent()) {
            Match match = matchById.get();
            bet.setMatch(match);
            bet.calculatePossibleWin();
            bookmakerService.save(bet);
            model.addAttribute("bet", bet);
            return "betSuccess";
        }

        return "redirect:/";
    }

    @GetMapping("/match/score/{id}")
    public String showFormToAddScoreMatch(@PathVariable Long id, Model model) {
        Optional<Match> matchById = bookmakerService.findMatchById(id);

        if (matchById.isPresent()) {
            Match match = matchById.get();
            model.addAttribute("match", match);
            return "scoreMatch";
        }
        return "redirect:/";
    }

    @PostMapping("/match/score")
    public String addScoreMatch(@ModelAttribute Match match, @RequestParam Long matchId) {
        Optional<Match> matchById = bookmakerService.findMatchById(matchId);

        if (matchById.isPresent()) {
            Match matchFromDb = matchById.get();
            MatchDetails matchDetails = matchFromDb.getMatchDetails();
            matchDetails.setScoreTeamA(match.getMatchDetails().getScoreTeamA());
            matchDetails.setScoreTeamB(match.getMatchDetails().getScoreTeamB());
            matchFromDb.setBettingClosed(true);
            matchFromDb.setResultWhenScoreIsKnown();
            bookmakerService.save(matchFromDb);
        }

        return "redirect:/";
    }
}
