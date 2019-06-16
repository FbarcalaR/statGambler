package com.statGambler.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.statGambler.model.Euromillones;
import com.statGambler.repository.EuromillonesRepository;
import com.statGambler.Math.Math;

@Service("EuromillonesService")
public class EuromillonesService {

	@Autowired
	EuromillonesRepository euromillonesRepository;

	public double probabilidad5y2;
	public double probabilidad5y1;
	public double probabilidad5;
	public double probabilidad4y2;
	public double probabilidad4y1;
	public double probabilidad3y2;
	public double probabilidad4;
	public double probabilidad2y2;
	public double probabilidad3y1;
	public double probabilidad3;
	public double probabilidad1y2;
	public double probabilidad2y1;
	public double probabilidad2;
	public double[] promedioNumero;
	public double[] promedioEstrella;
	public int aparicionesNumero[];
	public int aparicionesEstrella[];
	public double esperanza;
	public double mediaResultados;
	public double mediaComplementos;

	
	public double calcularProbabilidadesVictoria() {
		double espacioMuestral=(double)Math.CInt(50,5)*Math.CInt(9,2);
		probabilidad5y2 = Math.redondeo(1/espacioMuestral);
		probabilidad5y1 = Math.redondeo((double)Math.CInt(5, 5)*Math.CInt(2, 1)*Math.CInt(7, 1)/espacioMuestral);
		probabilidad5 =  Math.redondeo((double)Math.CInt(5, 5)*Math.CInt(7, 2) /espacioMuestral);
		probabilidad4y2 = Math.redondeo((double)Math.CInt(5, 4)*Math.CInt(45, 1)*Math.CInt(2, 2) /espacioMuestral);
		probabilidad4y1 = Math.redondeo((double)Math.CInt(5, 4)*Math.CInt(45, 1)*Math.CInt(2, 1)*Math.CInt(7, 1) /espacioMuestral);
		probabilidad3y2 = Math.redondeo((double)Math.CInt(5, 3)*Math.CInt(45, 2)*Math.CInt(2, 2) /espacioMuestral);
		probabilidad4 = Math.redondeo((double)Math.CInt(5, 4)*Math.CInt(45, 1)*Math.CInt(7, 2) /espacioMuestral);
		probabilidad2y2 =  Math.redondeo((double)Math.CInt(5,2)*Math.CInt(45, 3)* Math.CInt(2,2)/espacioMuestral);
		probabilidad3y1 = Math.redondeo((double)Math.CInt(5, 3)*Math.CInt(45, 2)*Math.CInt(2, 1)*Math.CInt(7, 1)/espacioMuestral);
		probabilidad3 = Math.redondeo((double)Math.CInt(5, 3)*Math.CInt(45, 2)*Math.CInt(7, 2) /espacioMuestral);
		probabilidad1y2 = Math.redondeo((double)Math.CInt(5,1)*Math.CInt(45,4)*Math.CInt(2,2) /espacioMuestral);
		probabilidad2y1 = Math.redondeo((double)Math.CInt(5,2)*Math.CInt(45, 3)*Math.CInt(2, 1)*Math.CInt(7, 1)/espacioMuestral);
		probabilidad2 = Math.redondeo((double)Math.CInt(5,2)*Math.CInt(45, 3)*Math.CInt(7, 2) /espacioMuestral);
		return 0;
	}

	
	public double calcularPromedioNumero() {
		int totalEstrella = (int) (euromillonesRepository.count());
		int totalNumeros = totalEstrella * 6;
		promedioNumero = new double[50];
		promedioEstrella = new double[12];

		for (int i = 0; i < aparicionesNumero.length; i++) {
			promedioNumero[i] = Math.redondeo(((double) aparicionesNumero[i] / (double) totalNumeros) * 100);
		}
		for (int i = 0; i < aparicionesEstrella.length; i++) {
			promedioEstrella[i] = Math.redondeo(((double) aparicionesEstrella[i] / (double) totalEstrella) * 100);
		}

		return promedioEstrella[0];
	}

	
	public double calcularApariciones() {
		aparicionesNumero = new int[50];
		aparicionesEstrella = new int[12];

		for (Euromillones p : euromillonesRepository.findAll()) {
			aparicionesNumero[p.getResultado0() - 1]++;
			aparicionesNumero[p.getResultado1() - 1]++;
			aparicionesNumero[p.getResultado2() - 1]++;
			aparicionesNumero[p.getResultado3() - 1]++;
			aparicionesNumero[p.getResultado4() - 1]++;
			aparicionesEstrella[p.getEstrella0() - 1]++;
			aparicionesEstrella[p.getEstrella1() - 1]++;
		}
		return aparicionesNumero[0];
	}


	
	public double calcularEsperanza() {
		esperanza=0.5;
		return esperanza;
	}

	
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
		mediaResultados = Math.redondeo(mediaResultados / total);
		return mediaResultados;
	}

	
	public double calcularMediaComplementos() {
		mediaComplementos = 0;
		int total = 0;

		for (Euromillones p : euromillonesRepository.findAll()) {
			mediaComplementos += p.getEstrella0();
			mediaComplementos += p.getEstrella1();
			total = total + 2;
		}
		mediaComplementos = Math.redondeo(mediaComplementos / total);
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
