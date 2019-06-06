package com.statGambler.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.statGambler.model.Euromillones;
import com.statGambler.model.Ruleta;
import com.statGambler.repository.RuletaRepository;

@Service("RuletaService")
public class RuletaService{

	@Autowired
	RuletaRepository ruletaRepository;
	
	public double probabilidadPleno;
	public double probabilidadCaballo;
	public double probabilidadTransversal;
	public double probabilidadFilaDoble;
	public double probabilidadCuadro;
	public double probabilidadDocenas;
	public double probabilidadColumnas;
	public double probabilidadColor;
	public double probabilidadParImpar;
	public double probabilidad1_18;
	public double probabilidad19_36;
	
	public double esperanzaPleno;
	public double esperanzaCaballo;
	public double esperanzaTransversal;
	public double esperanzaFilaDoble;
	public double esperanzaCuadro;
	public double esperanzaDocenas;
	public double esperanzaColumnas;
	public double esperanzaColor;
	public double esperanzaParImpar;
	public double esperanza1_18;
	public double esperanza19_36;
	
	public double apuesta;
	public double promedioNumero[];
	public int aparicionesNumero[];

	
	public double calcularProbabilidadesVictoria() {
		probabilidadPleno=1.0/37.0;
		probabilidadCaballo=2.0/37.0;
		probabilidadTransversal=3.0/37.0;
		probabilidadFilaDoble=6.0/37.0;
		probabilidadCuadro=4.0/37.0;
		probabilidadDocenas=12.0/37.0;
		probabilidadColumnas=12.0/37.0;
		probabilidadColor=18.0/37.0;
		probabilidadParImpar=18.0/37.0;
		probabilidad1_18=18.0/37.0;
		probabilidad19_36=18.0/37.0;
		return probabilidadPleno;
	}

	
	public double calcularPromedioNumero() {
		int totalJugadas = (int) (ruletaRepository.count());
		promedioNumero = new double[36];

		for (int i = 0; i < promedioNumero.length; i++) {
			promedioNumero[i] = ((double) aparicionesNumero[i] / (double) totalJugadas) * 100;
		}
		return promedioNumero[0];
	}

	
	public double calcularApariciones() {
		aparicionesNumero = new int[36];

		for (Ruleta r : ruletaRepository.findAll()) {
			aparicionesNumero[r.getNumero() - 1]++;
		}
		return aparicionesNumero[0];
	}
	
	public double calcularEsperanza() {
		esperanzaPleno=apuesta*36.0*probabilidadPleno;
		esperanzaCaballo=apuesta*18.0*probabilidadCaballo;
		esperanzaTransversal=apuesta*12.0*probabilidadTransversal;
		esperanzaFilaDoble=apuesta*6.0*probabilidadFilaDoble;
		esperanzaCuadro=apuesta*9.0*probabilidadCuadro;
		esperanzaDocenas=apuesta*3.0*probabilidadDocenas;
		esperanzaColumnas=apuesta*3.0*probabilidadColumnas;
		esperanzaColor=apuesta*2.0*probabilidadColor;
		esperanzaParImpar=apuesta*2.0*probabilidadParImpar;
		esperanza1_18=apuesta*2.0*probabilidad1_18;
		esperanza19_36=apuesta*2.0*probabilidad19_36;
		return esperanzaPleno;
	}
	

	public void setApuesta(double apuesta) {
		this.apuesta = apuesta;
	}
	
	public double getApuesta() {
		return apuesta;
	}
	
	public void calcularTodo() {
		calcularProbabilidadesVictoria();
		calcularApariciones();
		calcularPromedioNumero();
		calcularEsperanza();
	}
}
