package com.statGambler.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.statGambler.model.Euromillones;
import com.statGambler.repository.EuromillonesRepository;

@Service("EuromillonesService")
public class EuromillonesService extends GameService{

	@Autowired
	EuromillonesRepository euromillonesRepository;
	
	@Override
	public void CalcularMediaResultados() {
		mediaResultados=0;
		int total=0;
		
		for(Euromillones p : euromillonesRepository.findAll()) {
			mediaResultados+=p.getResultado0();
			mediaResultados+=p.getResultado1();
			mediaResultados+=p.getResultado2();
			mediaResultados+=p.getResultado3();
			mediaResultados+=p.getResultado4();
			total=total+6;
		}
		mediaResultados=mediaResultados/total;
	}
	
	public void CalcularMediaComplementos() {
		mediaComplementos=0;
		int total=0;
		
		for(Euromillones p : euromillonesRepository.findAll()) {
			mediaComplementos+=p.getEstrella0();
			mediaComplementos+=p.getEstrella1();
			total=total+2;
		}
		mediaComplementos=mediaComplementos/total;
	}
}
