package com.statGambler.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.statGambler.model.Ruleta;
import com.statGambler.repository.RuletaRepository;

@Service("RuletaService")
public class RuletaService implements GameService{

	@Autowired
	RuletaRepository ruletaRepository;
	
	double probabilidad6yR;
	double probabilidad6;
	double probabilidad5yC;
	double probabilidad5;
	double probabilidad4;
	double probabilidad3;
	double promedioNumero;
	int apariciones;
	double bote;
	double esperanza;
	double mediaResultados;
	double mediaComplementos;

	@Override
	public double calcularProbabilidadesVictoria() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calcularPromedioNumero() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calcularApariciones() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double setBote(double bote) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calcularEsperanza() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calcularMediaResultados() {
		mediaResultados=0;
		int total=0;
		
		for(Ruleta p : ruletaRepository.findAll()) {
			mediaResultados+=p.getNumero();
			total++;
		}
		mediaResultados=mediaResultados/total;
		return 0;
	}

	@Override
	public double calcularMediaComplementos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void calcularTodo(double bote) {
		 calcularProbabilidadesVictoria();
			
			calcularPromedioNumero();
			
			calcularApariciones();

			setBote(bote);
				
			calcularEsperanza();

			calcularMediaResultados();
			
			calcularMediaComplementos();
		
	}
}
