package com.statGambler.model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Primitiva{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int resultado0;
	private int resultado1;
	private int resultado2;
	private int resultado3;
	private int resultado4;
	private int resultado5;
	private int complementario;
	private int reintegro;
	
	public int getResultado0() {
		return resultado0;
	}
	public void setResultado0(int resultado0) {
		this.resultado0 = resultado0;
	}
	public int getResultado1() {
		return resultado1;
	}
	public void setResultado1(int resultado1) {
		this.resultado1 = resultado1;
	}
	public int getResultado2() {
		return resultado2;
	}
	public void setResultado2(int resultado2) {
		this.resultado2 = resultado2;
	}
	public int getResultado3() {
		return resultado3;
	}
	public void setResultado3(int resultado3) {
		this.resultado3 = resultado3;
	}
	public int getResultado4() {
		return resultado4;
	}
	public void setResultado4(int resultado4) {
		this.resultado4 = resultado4;
	}
	public int getResultado5() {
		return resultado5;
	}
	public void setResultado5(int resultado5) {
		this.resultado5 = resultado5;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
