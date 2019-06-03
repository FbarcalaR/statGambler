package com.statGambler.services;

public class Estadisticas {
	public int numero;
	public int apariciones;
	public double promedio; 
	
	
	public Estadisticas(int numero, int apariciones, double promedio) {
		super();
		this.numero = numero;
		this.apariciones = apariciones;
		this.promedio = promedio;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getApariciones() {
		return apariciones;
	}
	public void setApariciones(int apariciones) {
		this.apariciones = apariciones;
	}
	public double getPromedio() {
		return promedio;
	}
	public void setPromedio(double promedio) {
		this.promedio = promedio;
	}

}
