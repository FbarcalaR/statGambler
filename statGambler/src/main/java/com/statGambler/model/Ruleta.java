package com.statGambler.model;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ruleta{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int numero;
	private String color;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	private void setcolor(int numero) {
		int[] rojos = new int[] {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};
		int[] negros = new int[] {2,4,6,8,10,11,13,17,20,22,24,26,28,29,31,33,35}; 
		if(Arrays.asList(rojos).contains(numero)) {
			color= "rojo";
		}
		else if(Arrays.asList(negros).contains(numero)) {
			color= "negro";
		}
		else
			color= "verde";
	}
	
	private String getcolor() {
		return color;
	}
}
