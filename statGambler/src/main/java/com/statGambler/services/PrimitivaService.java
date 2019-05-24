package com.statGambler.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.statGambler.model.Primitiva;
import com.statGambler.repository.PrimitivaRepository;

@Service("PrimitivaService")
public class PrimitivaService extends GameService{

	@Autowired
	PrimitivaRepository primitivaRepository;
	
	@Override
	public void CalcularMediaResultados() {
		mediaResultados=0;
		int total=0;
		
		for(Primitiva p : primitivaRepository.findAll()) {
			mediaResultados+=p.getResultado0();
			mediaResultados+=p.getResultado1();
			mediaResultados+=p.getResultado2();
			mediaResultados+=p.getResultado3();
			mediaResultados+=p.getResultado4();
			mediaResultados+=p.getResultado5();
			total=total+6;
		}
		mediaResultados=mediaResultados/total;
	}
}
