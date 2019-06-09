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
		probabilidad5y2 = 1/espacioMuestral;
		probabilidad5y1 = (double)Math.CInt(5, 5)*Math.CInt(2, 1)*Math.CInt(7, 1)/espacioMuestral;
		probabilidad5 =  (double)Math.CInt(5, 5)*Math.CInt(7, 2) /espacioMuestral;
		probabilidad4y2 = (double)Math.CInt(5, 4)*Math.CInt(45, 1)*Math.CInt(2, 2) /espacioMuestral;
		probabilidad4y1 = (double)Math.CInt(5, 4)*Math.CInt(45, 1)*Math.CInt(2, 1)*Math.CInt(7, 1) /espacioMuestral;
		probabilidad3y2 = (double)Math.CInt(5, 3)*Math.CInt(45, 2)*Math.CInt(2, 2) /espacioMuestral;
		probabilidad4 = (double)Math.CInt(5, 4)*Math.CInt(45, 1)*Math.CInt(7, 2) /espacioMuestral;
		probabilidad2y2 =  (double)Math.CInt(5,2)*Math.CInt(45, 3)* Math.CInt(2,2)/espacioMuestral;
		probabilidad3y1 = (double)Math.CInt(5, 3)*Math.CInt(45, 2)*Math.CInt(2, 1)*Math.CInt(7, 1)/espacioMuestral;
		probabilidad3 = (double)Math.CInt(5, 3)*Math.CInt(45, 2)*Math.CInt(7, 2) /espacioMuestral;
		probabilidad1y2 = (double)Math.CInt(5,1)*Math.CInt(45,4)*Math.CInt(2,2) /espacioMuestral;
		probabilidad2y1 = (double)Math.CInt(5,2)*Math.CInt(45, 3)*Math.CInt(2, 1)*Math.CInt(7, 1)/espacioMuestral;
		probabilidad2 = (double)Math.CInt(5,2)*Math.CInt(45, 3)*Math.CInt(7, 2) /espacioMuestral;
		return 0;
	}

	
	public double calcularPromedioNumero() {
		int totalEstrella = (int) (euromillonesRepository.count());
		int totalNumeros = totalEstrella * 6;
		promedioNumero = new double[50];
		promedioEstrella = new double[12];

		for (int i = 0; i < aparicionesNumero.length; i++) {
			promedioNumero[i] = ((double) aparicionesNumero[i] / (double) totalNumeros) * 100;
		}
		for (int i = 0; i < aparicionesEstrella.length; i++) {
			promedioEstrella[i] = ((double) aparicionesEstrella[i] / (double) totalEstrella) * 100;
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
//		double probabilidadPerder = 1 - probabilidad5y2 - probabilidad5y1 - probabilidad5 - probabilidad4y2
//				- probabilidad4y1 - probabilidad3y2 - probabilidad4 - probabilidad2y2 - probabilidad3y1 - probabilidad3
//				- probabilidad1y2 - probabilidad2y1 - probabilidad2;
//		esperanza = probabilidad5y2 * 49882300.07 + probabilidad5y1 * 443320.35 + probabilidad5 * 75703.23
//				+ probabilidad4y2 * 4322.36 + probabilidad4y1 * 198.61 + probabilidad3y2 * 79.39 + probabilidad4 * 89.87
//				+ probabilidad2y2 * 20.03 + probabilidad3y1 * 14.67 + probabilidad3 * 12.20 + probabilidad1y2 * 10.72
//				+ probabilidad2y1 * 8.08 + probabilidad2 * 4.16;
		
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
		mediaResultados = mediaResultados / total;
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
		mediaComplementos = mediaComplementos / total;
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
