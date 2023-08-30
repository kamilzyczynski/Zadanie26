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

    @GetMapping("/match/{id}/delete")
    public String deleteMatch(@PathVariable Long id, Model model) {
        Optional<Match> matchById = bookmakerService.findMatchById(id);

        if (matchById.isPresent()) {
            model.addAttribute("match", matchById.get());
            return "confirmDelete";
        }
        return "error";
    }

    @GetMapping("/match/{id}/delete/confirm")
    public String confirmDeletingMatch(@PathVariable Long id) {
        Optional<Match> matchById = bookmakerService.findMatchById(id);

        if (matchById.isPresent()) {
            Match match = matchById.get();
            bookmakerService.deleteMatch(match);
            return "redirect:/";
        }
        return "error";
    }

    @GetMapping("/match/{id}/score")
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
    public String addScoreMatch(@ModelAttribute Match match, @RequestParam Long matchId, Model model) {
        Optional<Match> matchById = bookmakerService.findMatchById(matchId);

        if (matchById.isPresent()) {
            Match matchFromDb = matchById.get();
            matchFromDb.setResult(match.getResult());
            matchFromDb.setBettingClosed(true);
            bookmakerService.save(matchFromDb);
        }

        return "redirect:/";
    }
}
