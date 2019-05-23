package com.statGambler.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Primitiva{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double probabilidad;
	public int complementario;
	public int reintegro;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getProbabilidad() {
		return probabilidad;
	}
	public void setProbabilidad(double probabilidad) {
		this.probabilidad = probabilidad;
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
