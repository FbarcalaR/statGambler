package com.statGambler.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.statGambler.model.Ruleta;
import com.statGambler.repository.RuletaRepository;

@Service("RuletaService")
public class RuletaService extends GameService{

	@Autowired
	RuletaRepository ruletaRepository;
	
	@Override
	public void CalcularMediaResultados() {
		mediaResultados=0;
		int total=0;
		
		for(Ruleta p : ruletaRepository.findAll()) {
			mediaResultados+=p.getNumero();
			total++;
		}
		mediaResultados=mediaResultados/total;
	}
}
