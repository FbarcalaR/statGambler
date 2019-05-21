package com.statGambler.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.statGambler.model.Game;
import com.statGambler.model.User;
import com.statGambler.repository.GameRepository;
import com.statGambler.repository.UserRepository;
import com.statGambler.services.GameService;

@Controller
public class LoginController {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	GameRepository gameRepository;
	@Autowired
	GameService gameService;
	
	@GetMapping("/index")
	public String getIndex(User u) {
        return "index";
    }
	
	@GetMapping("/")
	public String getRoot(User u) {
        return "login";
    }
	
    @PostMapping("/newuser")
    public String newUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "login";
        }
         
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "login";
    }
    
    @PostMapping("/login")
    public String logIn(@Valid User user, BindingResult result, Model model) {
    	if (result.hasErrors()) {
            return "login";
        }
    	
    	for(User u: userRepository.findAll()) {
			if(u.equals(user)) {
				gameService.CalcularProbabilidad();
				model.addAttribute("gameService", gameService);
				model.addAttribute("games", gameRepository.findAll());
				return "stats";
			}
		}
    	return "login";
    }
}