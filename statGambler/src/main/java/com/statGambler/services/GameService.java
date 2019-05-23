package com.statGambler.services;

import org.springframework.stereotype.Service;


public class GameService {
	public double probabilidad;
		
	public double getProbabilidad() {
		return probabilidad;
	}

	public void setProbabilidad(double probabilidad) {
		this.probabilidad = probabilidad;
	}

	public void CalcularProbabilidad() {
		probabilidad=2;
	}

}
