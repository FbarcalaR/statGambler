package com.statGambler.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.statGambler.model.Primitiva;
import com.statGambler.repository.PrimitivaRepository;
import com.statGambler.services.PrimitivaService;

@Controller
public class StatsController {
     
    @PostMapping("/getstats")
    public String getStats(@Valid Primitiva game, BindingResult result, Model model) {
        return "stats";
    }
    
}
