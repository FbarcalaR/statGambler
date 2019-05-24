package com.statGambler.services;

public class GameService {
	public double mediaResultados;
	public double mediaComplementos;
		
	public double getMediaComplementos() {
		return mediaComplementos;
	}

	public void setMediaComplementos(double mediaComplementos) {
		this.mediaComplementos = mediaComplementos;
	}

	public double getMediaResultados() {
		return mediaResultados;
	}

	public void setMediaResultados(double mediaResultados) {
		this.mediaResultados = mediaResultados;
	}

	public void CalcularMediaResultados() {
		mediaResultados=2;
	}
	
	public void CalcularMediaComplementos() {
		mediaResultados=2;
	}

}
