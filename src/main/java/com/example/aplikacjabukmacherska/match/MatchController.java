package com.example.aplikacjabukmacherska.match;

import com.example.aplikacjabukmacherska.BookmakerService;
import com.example.aplikacjabukmacherska.bet.Bet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "error";
    }

    @PostMapping("/match/bet")
    public String betMatch(Bet bet, @RequestParam Long matchId) {
        Optional<Match> matchById = bookmakerService.findMatchById(matchId);

        if (matchById.isPresent()) {
            Match match = matchById.get();
            bet.setMatch(match);
            bookmakerService.save(bet);
        }

        return "redirect:/";
    }
}
