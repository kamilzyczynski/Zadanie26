package com.example.aplikacjabukmacherska;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private BookmakerService bookmakerService;

    public HomeController(BookmakerService bookmakerService) {
        this.bookmakerService = bookmakerService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("matches", bookmakerService.showMatchesAvailableForBetting());
        return "home";
    }
}
