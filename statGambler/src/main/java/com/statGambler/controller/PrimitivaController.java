package com.statGambler.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.statGambler.model.Primitiva;
import com.statGambler.repository.PrimitivaRepository;
import com.statGambler.services.PrimitivaService;

@Controller
public class PrimitivaController{
	
	@Autowired
	PrimitivaRepository primitivaRepository;
	@Autowired
	PrimitivaService primitivaService;
	
	@GetMapping("/primitivaform")
    public String showSignUpForm(Primitiva primitiva) {
        return "primitivas/add-primitiva";
    }
	
	@GetMapping("/primitivas")
    public String showPrimitivas(Model model) {
    	model.addAttribute("primitivas", primitivaRepository.findAll());
        return "primitivas/primitivas";
    }
     
    @PostMapping("/addprimitiva")
    public String addGame(@Valid Primitiva game, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "primitivas/add-primitiva";
        }
         
        primitivaRepository.save(game);
        model.addAttribute("primitivas", primitivaRepository.findAll());
        return "primitivas/primitivas";
    }
 
    @GetMapping("/editprimitiva/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Primitiva primitiva = primitivaRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid game Id:" + id));
         
        model.addAttribute("primitiva", primitiva);
        return "primitivas/update-primitiva";
    }
    
    @PostMapping("/updateprimitiva/{id}")
    public String updateGame(@PathVariable("id") long id, @Valid Primitiva game, 
      BindingResult result, Model model) {
        if (result.hasErrors()) {
            game.setId(id);
            return "primitivas/update-primitiva";
        }
             
        primitivaRepository.save(game);
        model.addAttribute("primitivas", primitivaRepository.findAll());
        return "primitivas/primitivas";
    }
         
    @GetMapping("/deleteprimitiva/{id}")
    public String deleteGame(@PathVariable("id") long id, Model model) {
        Primitiva game = primitivaRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid game Id:" + id));
        primitivaRepository.delete(game);
        model.addAttribute("primitivas", primitivaRepository.findAll());
        return "primitivas/primitivas";
    }
	
    @GetMapping("/primitivastats")
    public String showStats(Model model) {
		primitivaService.calcularTodo(1000.0);
		model.addAttribute("primitivaService", primitivaService);
		model.addAttribute("primitivas", primitivaRepository.findAll());
        return "primitivas/stats-primitiva";
    }
    
}
