package com.statGambler.controller;

import java.util.LinkedList;

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
import com.statGambler.services.MyUserDetailsService;
import com.statGambler.services.RuletaService;
import com.statGambler.validator.EuromillonesValidator;
import com.statGambler.validator.RuletaValidator;

@Controller
public class RuletaController {

	@Autowired
	RuletaRepository ruletaRepository;
	@Autowired
	RuletaService ruletaService;
	@Autowired
	private EstadisticasPersonalesService estadisticasPersonalesService;
	@Autowired
	private RuletaValidator ruletasValidator;
	@Autowired
	private MyUserDetailsService userPrincipal;

	@GetMapping("/ruletaform")
	public String showSignUpForm(Ruleta ruleta) {
		return "ruletas/add-ruleta";
	}

	@GetMapping("/ruletas")
	public String showRuletas(Model model) {
		modeladdRuletas(model);
		return "ruletas/ruletas";
	}

	@PostMapping("/addruleta")
	public String addGame(@Valid Ruleta game, BindingResult result, Model model) {
		ruletasValidator.validate(game, result);
		
		if (result.hasErrors()) {
			return showSignUpForm(new Ruleta());
		}

		game.setUserId(userPrincipal.getUserPrincipal().getId());
		ruletaRepository.save(game);
		modeladdRuletas(model);
		return "ruletas/ruletas";
	}

	@GetMapping("/editruleta/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Ruleta ruleta = ruletaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid game Id:" + id));

		
		model.addAttribute("ruleta", ruleta);
		return "ruletas/update-ruleta";
	}

	@PostMapping("/updateruleta/{id}")
	public String updateGame(@PathVariable("id") long id, @Valid Ruleta game, BindingResult result, Model model) {
		ruletasValidator.validate(game, result);
		if (result.hasErrors()) {
			game.setId(id);
			return "ruletas/update-ruleta";
		}

		game.setUserId(userPrincipal.getUserPrincipal().getId());
		ruletaRepository.save(game);
		modeladdRuletas(model);
		return "ruletas/ruletas";
	}

	@PostMapping("/apuesta")
	public String actualizaApuesta(@Valid Double apuesta, Model model) {
		ruletaService.setApuesta(apuesta);
		return showStats(model, apuesta);
	}

	@GetMapping("/deleteruleta/{id}")
	public String deleteGame(@PathVariable("id") long id, Model model) {
		Ruleta game = ruletaRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid game Id:" + id));
		ruletaRepository.delete(game);
		modeladdRuletas(model);
		return "ruletas/ruletas";
	}

	@GetMapping("/ruletastats")
	public String showStats(Model model, Double apuesta) {
		ruletaService.calcularTodo();
		model.addAttribute("ruletaService", ruletaService);
		modeladdRuletas(model);
		model.addAttribute("estadisticasPersonales", estadisticasPersonalesService.getEstadisticas());
		return "ruletas/stats-ruleta";
	}
	
	@GetMapping("/apuesta")
	public String showStatsApuesta(Model model, Double apuesta) {
		ruletaService.calcularTodo();
		model.addAttribute("ruletaService", ruletaService);
		 modeladdRuletas(model);
		model.addAttribute("estadisticasPersonales", estadisticasPersonalesService.getEstadisticas());
		return "ruletas/stats-ruleta";
	}
	
	@PostMapping("/postEstadisticasRuletas")
	public String postEstadisticas(@Valid EstadisticasPersonales eP, Model model) {
    	estadisticasPersonalesService.setEstadisticasRuletas(eP);
		return showStats(model, ruletaService.apuestaDouble);
	}
    
    @PostMapping("/postApuestaRuletas")
	public String postApuesta(@Valid EstadisticasPersonales eP, Model model, BindingResult result) {
    	eP.setDineroGanadoRuletas(eP.getDineroGanadoAux());
    	eP.setDineroGastadoRuletas(eP.getDineroGastadoAux());
    	
    	if(eP.getDineroGastadoRuletas()!=0.0 && eP.getDineroGanadoRuletas()!=0.0) {
    		estadisticasPersonalesService.setApuestaRuletas(eP);
    	}
    	model.addAttribute("estadisticasPersonales", eP);
    	Ruleta game= new Ruleta();
    	game.setNumero(eP.getResultadoRuleta());
    	game.setUserId(userPrincipal.getUserPrincipal().getId());
    	
    	ruletasValidator.validate(game, result);
		if (result.hasErrors()) {
			return showStats(model, ruletaService.apuestaDouble);
		}
    	
		ruletaRepository.save(game);
		modeladdRuletas(model);

    	eP.setDineroGanadoAux(0.0);
    	eP.setDineroGastadoAux(0.0);
		return showStats(model, ruletaService.apuestaDouble);
	}
    
    private void modeladdRuletas(Model model) {
    	LinkedList<Ruleta> ruletas=new LinkedList<Ruleta>();
		for(Ruleta r : ruletaRepository.findAll()) {
			if(r.getUserId()==userPrincipal.getUserPrincipal().getId()) {
				ruletas.add(r);
			}
		}
		
		
		model.addAttribute("ruletas", ruletas);
    }
}
