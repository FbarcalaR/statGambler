package com.statGambler.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.statGambler.model.EstadisticasPersonales;
import com.statGambler.model.Ruleta;
import com.statGambler.repository.RuletaRepository;
import com.statGambler.services.EstadisticasPersonalesService;
import com.statGambler.services.RuletaService;

@Controller
public class RuletaController {

	@Autowired
	RuletaRepository ruletaRepository;
	@Autowired
	RuletaService ruletaService;
	@Autowired
	private EstadisticasPersonalesService estadisticasPersonalesService;

	@GetMapping("/ruletaform")
	public String showSignUpForm(Ruleta ruleta) {
		return "ruletas/add-ruleta";
	}

	@GetMapping("/ruletas")
	public String showRuletas(Model model) {
		model.addAttribute("ruletas", ruletaRepository.findAll());
		return "ruletas/ruletas";
	}

	@PostMapping("/addresultadoruleta")
	public String addGame(@Valid Ruleta game, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "ruletas/add-ruleta";
		}

		ruletaRepository.save(game);
		model.addAttribute("ruletas", ruletaRepository.findAll());
		return "ruletas/ruletas";
	}

	@GetMapping("/editresultadoruleta/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Ruleta ruleta = ruletaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid game Id:" + id));

		model.addAttribute("ruleta", ruleta);
		return "ruletas/update-ruleta";
	}

	@PostMapping("/updateresultadoruleta/{id}")
	public String updateGame(@PathVariable("id") long id, @Valid Ruleta game, BindingResult result, Model model) {
		if (result.hasErrors()) {
			game.setId(id);
			return "ruletas/update-ruleta";
		}

		ruletaRepository.save(game);
		model.addAttribute("ruletas", ruletaRepository.findAll());
		return "ruletas/ruletas";
	}

	@PostMapping("/apuesta")
	public String actualizaApuesta(@Valid Double apuesta, Model model) {
		ruletaService.setApuesta(apuesta);
		ruletaService.calcularTodo();
		model.addAttribute("ruletaService", ruletaService);
		model.addAttribute("ruletas", ruletaRepository.findAll());
		return "ruletas/stats-ruleta";
	}

	@GetMapping("/deleteresultadoruleta/{id}")
	public String deleteGame(@PathVariable("id") long id, Model model) {
		Ruleta game = ruletaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid game Id:" + id));
		ruletaRepository.delete(game);
		model.addAttribute("ruletas", ruletaRepository.findAll());
		return "ruletas/ruletas";
	}

	@GetMapping("/ruletastats")
	public String showStats(Model model, Double apuesta) {
		ruletaService.calcularTodo();
		model.addAttribute("ruletaService", ruletaService);
		model.addAttribute("ruletas", ruletaRepository.findAll());
		model.addAttribute("estadisticasPersonales", estadisticasPersonalesService.getEstadisticas());
		return "ruletas/stats-ruleta";
	}
	
	@GetMapping("/apuesta")
	public String showStatsApuesta(Model model, Double apuesta) {
		ruletaService.calcularTodo();
		model.addAttribute("ruletaService", ruletaService);
		model.addAttribute("ruletas", ruletaRepository.findAll());
		model.addAttribute("estadisticasPersonales", estadisticasPersonalesService.getEstadisticas());
		return "ruletas/stats-ruleta";
	}
	
	@PostMapping("/postEstadisticasRuletas")
	public String postEstadisticas(@Valid EstadisticasPersonales eP, Model model) {
    	estadisticasPersonalesService.setEstadisticasRuletas(eP);
		model.addAttribute("estadisticasPersonales", eP);
		return showStats(model, ruletaService.apuestaDouble);
	}
    
    @PostMapping("/postApuestaRuletas")
	public String postApuesta(@Valid EstadisticasPersonales eP, Model model) {
    	estadisticasPersonalesService.setApuestaRuletas(eP);
    	model.addAttribute("estadisticasPersonales", eP);
		return showStats(model, ruletaService.apuestaDouble);
	}

}
