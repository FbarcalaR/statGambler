package com.statGambler.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.statGambler.model.Game;
import com.statGambler.repository.GameRepository;
import com.statGambler.services.GameService;

@Controller
public class StatsController {

	@Autowired
	GameRepository gameRepository;
	@Autowired
	GameService gameService;
	
	@GetMapping("/showstats")
    public String showStats(Model model) {
		gameService.CalcularProbabilidad();
		model.addAttribute("gameService", gameService);
		model.addAttribute("games", gameRepository.findAll());
        return "stats";
    }
     
    @PostMapping("/getstats")
    public String getStats(@Valid Game game, BindingResult result, Model model) {
        return "stats";
    }
    
    @GetMapping("/games")
    public String showGames(Model model) {
    	model.addAttribute("games", gameRepository.findAll());
        return "games";
    }
    
}
