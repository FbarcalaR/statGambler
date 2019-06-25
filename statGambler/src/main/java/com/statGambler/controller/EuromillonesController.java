package com.statGambler.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.statGambler.model.Euromillones;
import com.statGambler.repository.EuromillonesRepository;
import com.statGambler.services.EuromillonesService;

@Controller
public class EuromillonesController{
	
	@Autowired
	EuromillonesRepository euromillonesRepository;
	@Autowired
	EuromillonesService euromillonesService;
	
	@GetMapping("/euromillonesform")
    public String showSignUpForm(Euromillones euromillones) {
        return "euromillones/add-euromillones";
    }
	
	@GetMapping("/euromillones")
    public String showEuromilloness(Model model) {
    	model.addAttribute("euromillones", euromillonesRepository.findAll());
        return "euromillones/euromillones";
    }
	
    @PostMapping("/addeuromillones")
    public String addGame(@Valid Euromillones game, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "euromillones/add-euromillones";
        }
         
        euromillonesRepository.save(game);
        model.addAttribute("euromillones", euromillonesRepository.findAll());
        return "euromillones/euromillones";
    }
 
    @GetMapping("/editeuromillones/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Euromillones euromillones = euromillonesRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid game Id:" + id));
         
        model.addAttribute("euromillones", euromillones);
        return "euromillones/update-euromillones";
    }
    
    @PostMapping("/updateeuromillones/{id}")
    public String updateGame(@PathVariable("id") long id, @Valid Euromillones game, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            game.setId(id);
            return "euromillones/update-euromillones";
        }
             
        euromillonesRepository.save(game);
        model.addAttribute("euromillones", euromillonesRepository.findAll());
        return "euromillones/euromillones";
    }
         
    @GetMapping("/deleteeuromillones/{id}")
    public String deleteGame(@PathVariable("id") long id, Model model) {
        Euromillones game = euromillonesRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid game Id:" + id));
        euromillonesRepository.delete(game);
        model.addAttribute("euromillones", euromillonesRepository.findAll());
        return "euromillones/euromillones";
    }
	
    @GetMapping("/euromillonesstats")
    public String showStats(Model model) {
		euromillonesService.calcularTodo(1000.0);
		model.addAttribute("euromillonesService", euromillonesService);
		model.addAttribute("euromillones", euromillonesRepository.findAll());
        return "euromillones/stats-euromillones";
    }
    
}
