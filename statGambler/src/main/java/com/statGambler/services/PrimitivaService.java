package com.statGambler.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.statGambler.model.Primitiva;
import com.statGambler.repository.PrimitivaRepository;

@Service("PrimitivaService")
public class PrimitivaService implements GameService{

	@Autowired
	PrimitivaRepository primitivaRepository;
	
	
	public double probabilidad6yR;
	public double probabilidad6;
	public double probabilidad5yC;
	public double probabilidad5;
	public double probabilidad4;
	public double probabilidad3;
	public double probabilidadR;
	public double[] promedioNumero;
	public double promedioReintegro[];
	public int aparicionesNumero[];
	public int aparicionesReintegro[];
	public double bote;
	public double esperanza;
	public double mediaResultados;
	public double mediaComplementos;

	@Override
	public double calcularProbabilidadesVictoria() {
		// TODO Auto-generated method stub
		probabilidad6yR=(6.0*5.0*4.0*3.0*2.0*1.0*1.0)/(49.0*48.0*47.0*46.0*45.0*44.0*10.0);
		probabilidad6=(6.0*5.0*4.0*3.0*2.0*1.0)/(49.0*48.0*47.0*46.0*45.0*44.0);
		probabilidad5yC=6.0*(6.0*5.0*4.0*3.0*2.0*1.0)/(49.0*48.0*47.0*46.0*45.0*44.0);
		probabilidad5=6.0*(6.0*5.0*4.0*3.0*2.0*42.0)/(49.0*48.0*47.0*46.0*45.0*44.0);
		probabilidad4=15*(6.0*5.0*4.0*3.0*43.0*42.0)/(49.0*48.0*47.0*46.0*45.0*44.0);
		probabilidad3=20*(6.0*5.0*4.0*43.0*42.0*41.0)/(49.0*48.0*47.0*46.0*45.0*44.0);
		probabilidadR=1/10;
		return probabilidad6yR;
	}

	@Override
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

	@Override
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
			aparicionesReintegro[p.getReintegro()-1]++;
		}
		return aparicionesNumero[0];
	}

	@Override
	public double setBote(double bote) {
		this.bote=bote;
		return bote;
	}

	@Override
	public double calcularEsperanza() {
		return probabilidad6*bote;
	}

	@Override
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

	@Override
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

	@Override
	public void calcularTodo(double bote) {
		calcularProbabilidadesVictoria();
		calcularApariciones();
		calcularPromedioNumero();
		setBote(bote);			
		calcularEsperanza();
		calcularMediaResultados();		
		calcularMediaComplementos();
	}
}
