package com.statGambler.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.statGambler.Math.Math;
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
	public double probabilidadPasaFalta;
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
	public double esperanzaPasaFalta;
	
	public double apuesta;
	public double promedioNumero[];
	public int aparicionesNumero[];

	
	public double calcularProbabilidadesVictoria() {
		probabilidadPleno=Math.redondeo(1.0/37.0);
		probabilidadCaballo=Math.redondeo(2.0/37.0);
		probabilidadTransversal=Math.redondeo(3.0/37.0);
		probabilidadFilaDoble=Math.redondeo(6.0/37.0);
		probabilidadCuadro=Math.redondeo(4.0/37.0);
		probabilidadDocenas=Math.redondeo(12.0/37.0);
		probabilidadColumnas=Math.redondeo(12.0/37.0);
		probabilidadColor=Math.redondeo(18.0/37.0);
		probabilidadParImpar=Math.redondeo(18.0/37.0);
		probabilidadPasaFalta=Math.redondeo(18.0/37.0);
		return probabilidadPleno;
	}

	
	public double calcularPromedioNumero() {
		int totalJugadas = (int) (ruletaRepository.count());
		promedioNumero = new double[36];

		for (int i = 0; i < promedioNumero.length; i++) {
			promedioNumero[i] = Math.redondeo(((double) aparicionesNumero[i] / (double) totalJugadas) * 100);
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
		esperanzaPleno=Math.redondeo(probabilidadPleno*apuesta*35+(1-probabilidadPleno)*(-apuesta));
		esperanzaCaballo=Math.redondeo(probabilidadCaballo*apuesta*17+(1-probabilidadCaballo)*(-apuesta));
		esperanzaTransversal=Math.redondeo(probabilidadTransversal*apuesta*11+(1-probabilidadTransversal)*(-apuesta));
		esperanzaFilaDoble=Math.redondeo(probabilidadFilaDoble*apuesta*5+(1-probabilidadFilaDoble)*(-apuesta));
		esperanzaCuadro=Math.redondeo(probabilidadCuadro*apuesta*8+(1-probabilidadCuadro)*(-apuesta));
		esperanzaDocenas=Math.redondeo(probabilidadDocenas*apuesta*2+(1-probabilidadDocenas)*(-apuesta));
		esperanzaColumnas=Math.redondeo(probabilidadColumnas*apuesta*2+(1-probabilidadColumnas)*(-apuesta));
		esperanzaColor=Math.redondeo(probabilidadColor*apuesta*1-(18.0/37.0)*apuesta-(1.0/37.0*0.5)*apuesta);
		esperanzaParImpar=Math.redondeo(probabilidadParImpar*apuesta*1-(18.0/37.0)*apuesta-(1.0/37.0*0.5)*apuesta);
		esperanzaPasaFalta=Math.redondeo(probabilidadPasaFalta*apuesta*1-(18.0/37.0)*apuesta-(1.0/37.0*0.5)*apuesta);
		
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
