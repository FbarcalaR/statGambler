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
import com.statGambler.model.Euromillones;
import com.statGambler.repository.EuromillonesRepository;
import com.statGambler.services.EstadisticasPersonalesService;
import com.statGambler.services.EuromillonesService;
import com.statGambler.validator.EuromillonesValidator;

@Controller
public class EuromillonesController{
	
	@Autowired
	private EuromillonesRepository euromillonesRepository;
	@Autowired
	private EuromillonesService euromillonesService;
	@Autowired
	private EstadisticasPersonalesService estadisticasPersonalesService;
	@Autowired
	private EuromillonesValidator euromillonesValidator;
	
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
    	euromillonesValidator.validate(game, result);
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
    	euromillonesValidator.validate(game, result);
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
		euromillonesService.calcularTodo();
		model.addAttribute("euromillonesService", euromillonesService);
		model.addAttribute("euromillones", euromillonesRepository.findAll());
		model.addAttribute("estadisticasPersonales", estadisticasPersonalesService.getEstadisticas());
		System.out.println(estadisticasPersonalesService.getEstadisticas().getDineroGastadoEuromillones());
        return "euromillones/stats-euromillones";
    }
    
    @PostMapping("/postEstadisticasEuromillones")
	public String postEstadisticas(@Valid EstadisticasPersonales eP, Model model) {
    	estadisticasPersonalesService.setEstadisticasEuromillones(eP);
		return showStats(model);
	}
    
    @PostMapping("/postApuestaEuromillones")
	public String postApuesta(@Valid EstadisticasPersonales eP, Model model) {
		System.out.println("estadistica nuevas: "+ eP.getDineroGanadoEuromillones()+ " "+eP.getDineroGastadoEuromillones());
		System.out.println("estadistica nuevasprim: "+ eP.getDineroGanadoPrimitivas()+ " "+eP.getDineroGastadoPrimitivas());
    	
    	eP.setDineroGanadoEuromillones(eP.getDineroGanadoAux());
    	eP.setDineroGastadoEuromillones(eP.getDineroGastadoAux());
    	System.out.println(eP.getDineroGanadoEuromillones() + " "+ eP.getDineroGastadoEuromillones());
    	
    	estadisticasPersonalesService.setApuestaEuromillones(eP);

    	eP.setDineroGanadoAux(0.0);
    	eP.setDineroGastadoAux(0.0);

		System.out.println("estadistica nuevas: "+ eP.getDineroGanadoEuromillones()+ " "+eP.getDineroGastadoEuromillones());
		System.out.println("estadistica nuevasprim: "+ eP.getDineroGanadoPrimitivas()+ " "+eP.getDineroGastadoPrimitivas());
		return showStats(model);
	}
    
}
