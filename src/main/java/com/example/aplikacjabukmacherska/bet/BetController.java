package com.example.aplikacjabukmacherska.bet;

import com.example.aplikacjabukmacherska.BookmakerService;
import com.example.aplikacjabukmacherska.match.Match;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class BetController {
    private BookmakerService bookmakerService;

    public BetController(BookmakerService bookmakerService) {
        this.bookmakerService = bookmakerService;
    }

    @GetMapping("/bet/archive")
    public String showAllBets(Model model) {
        List<Bet> bets = bookmakerService.findAll();
        model.addAttribute("bets", bets);

        return "betsArchive";
    }

    @PostMapping("/match/bet")
    public String betMatch(Bet bet, @RequestParam Long matchId) {
        Long betId = bookmakerService.makeBet(bet, matchId);

        if (betId != null) {
            return "redirect:/bet/%s/details".formatted(betId);
        } else {
            return "error";
        }
    }

    @GetMapping("/match/{id}/bet")
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

    @GetMapping("bet/{id}/details")
    String showBetDetails(@PathVariable Long id, Model model) {
        Optional<Bet> betById = bookmakerService.findBetById(id);

        if (betById.isPresent()) {
            model.addAttribute("bet", betById.get());
            return "betDetails";
        }
        return "error";
    }
}
