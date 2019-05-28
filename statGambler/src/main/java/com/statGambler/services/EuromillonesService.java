package com.statGambler.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.statGambler.model.Euromillones;
import com.statGambler.model.Primitiva;
import com.statGambler.repository.EuromillonesRepository;

@Service("EuromillonesService")
public class EuromillonesService implements GameService {

	@Autowired
	EuromillonesRepository euromillonesRepository;

	public double probabilidad6yR;
	public double probabilidad6;
	public double probabilidad5yC;
	public double probabilidad5;
	public double probabilidad4;
	public double probabilidad3;
	public double[] promedioNumero;
	public double[] promedioEstrella;
	public int aparicionesNumero[];
	public int aparicionesEstrella[];
	public double bote;
	public double esperanza;
	public double mediaResultados;
	public double mediaComplementos;

	@Override
	public double calcularProbabilidadesVictoria() {
		 probabilidad6yR=(6*5*4*3*2*1)/(50*49*48*47*46*45*44);
		 probabilidad6=(6*5*4*3*2*1)/(50*49*48*47*46*45*44);
		 probabilidad5yC=(6*5*4*3*2*1)/(50*49*48*47*46*45*44);
		 probabilidad5=(6*5*4*3*2*1)/(50*49*48*47*46*45*44);
		 probabilidad4=(6*5*4*3*2*1)/(50*49*48*47*46*45*44);
		 probabilidad3=(6*5*4*3*2*1)/(50*49*48*47*46*45*44);
		return 0;
	}

	@Override
	public double calcularPromedioNumero() {
		int totalEstrella = (int) (euromillonesRepository.count());
		int totalNumeros = totalEstrella * 6;
		promedioNumero = new double[49];
		promedioEstrella = new double[10];

		for (int i = 0; i < aparicionesNumero.length; i++) {
			promedioNumero[i] = ((double) aparicionesNumero[i] / (double) totalNumeros) * 100;
		}
		for (int i = 0; i < aparicionesEstrella.length; i++) {
			promedioEstrella[i] = ((double) aparicionesEstrella[i] / (double) totalEstrella) * 100;
		}

		return promedioEstrella[0];
	}

	@Override
	public double calcularApariciones() {
		aparicionesNumero=new int[49];
		aparicionesEstrella=new int[10];
		
		for(Euromillones p : euromillonesRepository.findAll()) {
			aparicionesNumero[p.getResultado0()-1]++;
			aparicionesNumero[p.getResultado1()-1]++;
			aparicionesNumero[p.getResultado2()-1]++;
			aparicionesNumero[p.getResultado3()-1]++;
			aparicionesNumero[p.getResultado4()-1]++;
			aparicionesEstrella[p.getEstrella0()-1]++;
			aparicionesEstrella[p.getEstrella1()-1]++;
		}
		return aparicionesNumero[0];
	}

	@Override
	public double setBote(double bote) {
		this.bote = bote;
		return bote;
	}

	@Override
	public double calcularEsperanza() {
		return probabilidad6 * bote;
	}

	@Override
	public double calcularMediaResultados() {
		mediaResultados = 0;
		int total = 0;

		for (Euromillones p : euromillonesRepository.findAll()) {
			mediaResultados += p.getResultado0();
			mediaResultados += p.getResultado1();
			mediaResultados += p.getResultado2();
			mediaResultados += p.getResultado3();
			mediaResultados += p.getResultado4();
			total = total + 6;
		}
		mediaResultados = mediaResultados / total;
		return mediaResultados;
	}

	@Override
	public double calcularMediaComplementos() {
		mediaComplementos = 0;
		int total = 0;

		for (Euromillones p : euromillonesRepository.findAll()) {
			mediaComplementos += p.getEstrella0();
			mediaComplementos += p.getEstrella1();
			total = total + 2;
		}
		mediaComplementos = mediaComplementos / total;
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
