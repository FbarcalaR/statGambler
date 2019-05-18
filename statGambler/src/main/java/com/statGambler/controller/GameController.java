package com.statGambler.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.statGambler.model.Game;
import com.statGambler.repository.GameRepository;


@Controller
public class GameController {
	
	@Autowired
	GameRepository gameRepository;
     
    @GetMapping("/gameform")
    public String showSignUpForm(Game game) {
        return "add-game";
    }
     
    @PostMapping("/addgame")
    public String addGame(@Valid Game game, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-game";
        }
         
        gameRepository.save(game);
        model.addAttribute("games", gameRepository.findAll());
        return "index";
    }
 
    @GetMapping("/editgame/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Game game = gameRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid game Id:" + id));
         
        model.addAttribute("game", game);
        return "update-game";
    }
    
    @PostMapping("/updategame/{id}")
    public String updateGame(@PathVariable("id") long id, @Valid Game game, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            game.setId(id);
            return "update-game";
        }
             
        gameRepository.save(game);
        model.addAttribute("games", gameRepository.findAll());
        return "index";
    }
         
    @GetMapping("/deletegame/{id}")
    public String deleteGame(@PathVariable("id") long id, Model model) {
        Game game = gameRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid game Id:" + id));
        gameRepository.delete(game);
        model.addAttribute("games", gameRepository.findAll());
        return "index";
    }
}