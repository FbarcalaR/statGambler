package com.statGambler.model;

import javax.persistence.Entity;

@Entity
public class Primitiva extends Game {
	
//	public int[] resultados = new int[6];
	public int complementario;
	public int reintegro;
	
	public int getComplementario() {
		return complementario;
	}
	public void setComplementario(int complementario) {
		this.complementario = complementario;
	}
	public int getReintegro() {
		return reintegro;
	}
	public void setReintegro(int reintegro) {
		this.reintegro = reintegro;
	}
	
	
}
