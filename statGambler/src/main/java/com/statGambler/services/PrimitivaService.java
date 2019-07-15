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
	
	public double probabilidad6Double;
	public double probabilidad5yCDouble;
	public double probabilidad5Double;
	public double probabilidad4Double;
	public double probabilidad3Double;
	public double probabilidad2Double;
	public double probabilidadRDouble;
	public double[] promedioNumeroDouble;
	public double promedioReintegroDouble[];
	public int aparicionesNumero[];
	public int aparicionesReintegro[];
	public double esperanzaDouble;
	
	public String probabilidad6;
	public String probabilidad5yC;
	public String probabilidad5;
	public String probabilidad4;
	public String probabilidad3;
	public String probabilidad2;
	public String probabilidadR;
	public String[] promedioNumero;
	public String promedioReintegro[];
	public String esperanza;

	
	public double calcularProbabilidadesVictoria() {
		double espacioMuestral=(double)Math.CInt(49,6)*Math.CInt(10,1);
		
		probabilidad6Double=1/espacioMuestral;
		probabilidad5yCDouble=Math.redondeo(Math.CInt(6, 5)*Math.CInt(1, 1)/espacioMuestral);
		probabilidad5Double=Math.redondeo(Math.CInt(6,5)*Math.CInt(42,1)/espacioMuestral);
		probabilidad4Double=Math.redondeo(Math.CInt(6,4)*Math.CInt(43,2)/espacioMuestral);
		probabilidad3Double=Math.redondeo(Math.CInt(6,3)*Math.CInt(44,3)*Math.CInt(9,1)/espacioMuestral);
		probabilidad2Double=Math.redondeo(Math.CInt(6,2)*Math.CInt(45,4)*Math.CInt(9,1)/espacioMuestral);
		probabilidadRDouble=Math.redondeo(1/10);
		return probabilidad6Double;
	}

	
	public double calcularPromedioNumero() {
		int totalReintegros=(int) (primitivaRepository.count());
		int totalNumeros=totalReintegros*6;
		promedioNumeroDouble=new double[49];
		promedioReintegroDouble=new double[10];
		
		
		for(int i=0;i<aparicionesNumero.length;i++) {
			promedioNumeroDouble[i]=Math.redondeoPromedios(((double)aparicionesNumero[i]/(double)totalNumeros)*100);
		}
		for(int i=0;i<aparicionesReintegro.length;i++) {
			promedioReintegroDouble[i]=Math.redondeoPromedios(((double)aparicionesReintegro[i]/(double)totalReintegros)*100);
		}
		
		return promedioReintegroDouble[0];
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
		esperanzaDouble=0.55;
		return esperanzaDouble;
	}

	
	public void calcularTodo(double bote) {
		calcularProbabilidadesVictoria();
		calcularApariciones();
		calcularPromedioNumero();
		calcularEsperanza();		
		
		probabilidad6=Math.doubleToString(probabilidad6Double);
		probabilidad5yC=Math.doubleToString(probabilidad5yCDouble);
		probabilidad5=Math.doubleToString(probabilidad5Double);
		probabilidad4=Math.doubleToString(probabilidad4Double);
		probabilidad3=Math.doubleToString(probabilidad3Double);
		probabilidad2=Math.doubleToString(probabilidad2Double);
		probabilidadR=Math.doubleToString(probabilidadRDouble);
		
		promedioNumero=new String[promedioNumeroDouble.length];
		int i =0;
		for(double d : promedioNumeroDouble) {
			promedioNumero[i]=Math.doubleToString(d)+" %";
			i++;
		}
		promedioReintegro=new String[promedioReintegroDouble.length];
		i =0;
		for(double d : promedioReintegroDouble) {
			promedioReintegro[i]=Math.doubleToString(d)+" %";
			i++;
		}
		
		esperanza=Math.doubleToString(esperanzaDouble);
	}
}
