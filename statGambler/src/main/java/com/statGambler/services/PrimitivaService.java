package com.statGambler.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.statGambler.Math.Math;
import com.statGambler.model.Primitiva;
import com.statGambler.repository.PrimitivaRepository;

@Service("PrimitivaService")
public class PrimitivaService {

	@Autowired
	PrimitivaRepository primitivaRepository;
	
	public double probabilidad6;
	public double probabilidad5yC;
	public double probabilidad5;
	public double probabilidad4;
	public double probabilidad3;
	public double probabilidad2;
	public double probabilidadR;
	public double[] promedioNumero;
	public double promedioReintegro[];
	public int aparicionesNumero[];
	public int aparicionesReintegro[];
	public double esperanza;
	public double mediaResultados;
	public double mediaComplementos;

	
	public double calcularProbabilidadesVictoria() {
		double espacioMuestral=(double)Math.CInt(49,6)*Math.CInt(10,1);
		
		probabilidad6=1/espacioMuestral;
		probabilidad5yC=Math.CInt(6, 5)*Math.CInt(1, 1)/espacioMuestral;
		probabilidad5=Math.CInt(6,5)*Math.CInt(42,1)/espacioMuestral;
		probabilidad4=Math.CInt(6,4)*Math.CInt(43,2)/espacioMuestral;
		probabilidad3=Math.CInt(6,3)*Math.CInt(44,3)*Math.CInt(9,1)/espacioMuestral;
		probabilidad2=Math.CInt(6,2)*Math.CInt(45,4)*Math.CInt(9,1)/espacioMuestral;
		probabilidadR=1/10;
		return probabilidad6;
	}

	
	public double calcularPromedioNumero() {
		int totalReintegros=(int) (primitivaRepository.count());
		int totalNumeros=totalReintegros*6;
		promedioNumero=new double[49];
		promedioReintegro=new double[10];
		
		
		for(int i=0;i<aparicionesNumero.length;i++) {
			promedioNumero[i]=((double)aparicionesNumero[i]/(double)totalNumeros)*100;
		}
		for(int i=0;i<aparicionesReintegro.length;i++) {
			promedioReintegro[i]=((double)aparicionesReintegro[i]/(double)totalReintegros)*100;
		}
		
		return promedioReintegro[0];
	}

	
	public double calcularApariciones() {
		aparicionesNumero=new int[49];
		aparicionesReintegro=new int[10];
		
		for(Primitiva p : primitivaRepository.findAll()) {
			aparicionesNumero[p.getResultado0()-1]++;
			aparicionesNumero[p.getResultado1()-1]++;
			aparicionesNumero[p.getResultado2()-1]++;
			aparicionesNumero[p.getResultado3()-1]++;
			aparicionesNumero[p.getResultado4()-1]++;
			aparicionesNumero[p.getResultado5()-1]++;
			aparicionesReintegro[p.getReintegro()]++;
		}
		return aparicionesNumero[0];
	}

	
	public double calcularEsperanza() {
//		double probabilidadPerder = 1 - probabilidad6 - probabilidad5yC - probabilidad5 - probabilidad4 - probabilidad3
//				- probabilidad2 - probabilidadR;
//
//		esperanza = probabilidad6 * 16368540.07 + probabilidad5yC * 1312914.45 + probabilidad5 * 63534.07
//				+ probabilidad4 * 2543.36 + probabilidad3 * 71.49 + probabilidad2 * 8 + probabilidadR * 1;
		
		esperanza=0.55;
		return esperanza;
	}

	
	public double calcularMediaResultados() {
		mediaResultados=0;
		int total=0;
		
		for(Primitiva p : primitivaRepository.findAll()) {
			mediaResultados+=p.getResultado0();
			mediaResultados+=p.getResultado1();
			mediaResultados+=p.getResultado2();
			mediaResultados+=p.getResultado3();
			mediaResultados+=p.getResultado4();
			mediaResultados+=p.getResultado5();
			total=total+6;
		}
		mediaResultados=mediaResultados/total;
		return mediaResultados;
	}

	
	public double calcularMediaComplementos() {
		mediaComplementos=0;
		int total=0;
		
		for(Primitiva p : primitivaRepository.findAll()) {
			mediaComplementos+=p.getReintegro();
			total++;
		}
		mediaComplementos=mediaComplementos/total;
		return mediaComplementos;
	}

	
	public void calcularTodo(double bote) {
		calcularProbabilidadesVictoria();
		calcularApariciones();
		calcularPromedioNumero();
		calcularEsperanza();
		calcularMediaResultados();		
		calcularMediaComplementos();
	}
}
