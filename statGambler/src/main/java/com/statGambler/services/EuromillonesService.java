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

	public double probabilidad5y2Double;
	public double probabilidad5y1Double;
	public double probabilidad5Double;
	public double probabilidad4y2Double;
	public double probabilidad4y1Double;
	public double probabilidad3y2Double;
	public double probabilidad4Double;
	public double probabilidad2y2Double;
	public double probabilidad3y1Double;
	public double probabilidad3Double;
	public double probabilidad1y2Double;
	public double probabilidad2y1Double;
	public double probabilidad2Double;
	public double[] promedioNumeroDouble;
	public double[] promedioEstrellaDouble;
	public int aparicionesNumero[];
	public int aparicionesEstrella[];
	public double esperanzaDouble;
	public double mediaResultadosDouble;
	public double mediaComplementosDouble;
	
	public String probabilidad5y2;
	public String probabilidad5y1;
	public String probabilidad5;
	public String probabilidad4y2;
	public String probabilidad4y1;
	public String probabilidad3y2;
	public String probabilidad4;
	public String probabilidad2y2;
	public String probabilidad3y1;
	public String probabilidad3;
	public String probabilidad1y2;
	public String probabilidad2y1;
	public String probabilidad2;
	public String[] promedioNumero;
	public String[] promedioEstrella;
	public String esperanza;
	public String mediaResultados;
	public String mediaComplementos;
	
	public double calcularProbabilidadesVictoria() {
		double espacioMuestral=(double)Math.CInt(50,5)*Math.CInt(9,2);
		probabilidad5y2Double = Math.redondeo(1/espacioMuestral);
		probabilidad5y1Double = Math.redondeo((double)Math.CInt(5, 5)*Math.CInt(2, 1)*Math.CInt(7, 1)/espacioMuestral);
		probabilidad5Double =  Math.redondeo((double)Math.CInt(5, 5)*Math.CInt(7, 2) /espacioMuestral);
		probabilidad4y2Double = Math.redondeo((double)Math.CInt(5, 4)*Math.CInt(45, 1)*Math.CInt(2, 2) /espacioMuestral);
		probabilidad4y1Double = Math.redondeo((double)Math.CInt(5, 4)*Math.CInt(45, 1)*Math.CInt(2, 1)*Math.CInt(7, 1) /espacioMuestral);
		probabilidad3y2Double = Math.redondeo((double)Math.CInt(5, 3)*Math.CInt(45, 2)*Math.CInt(2, 2) /espacioMuestral);
		probabilidad4Double = Math.redondeo((double)Math.CInt(5, 4)*Math.CInt(45, 1)*Math.CInt(7, 2) /espacioMuestral);
		probabilidad2y2Double =  Math.redondeo((double)Math.CInt(5,2)*Math.CInt(45, 3)* Math.CInt(2,2)/espacioMuestral);
		probabilidad3y1Double = Math.redondeo((double)Math.CInt(5, 3)*Math.CInt(45, 2)*Math.CInt(2, 1)*Math.CInt(7, 1)/espacioMuestral);
		probabilidad3Double = Math.redondeo((double)Math.CInt(5, 3)*Math.CInt(45, 2)*Math.CInt(7, 2) /espacioMuestral);
		probabilidad1y2Double = Math.redondeo((double)Math.CInt(5,1)*Math.CInt(45,4)*Math.CInt(2,2) /espacioMuestral);
		probabilidad2y1Double = Math.redondeo((double)Math.CInt(5,2)*Math.CInt(45, 3)*Math.CInt(2, 1)*Math.CInt(7, 1)/espacioMuestral);
		probabilidad2Double = Math.redondeo((double)Math.CInt(5,2)*Math.CInt(45, 3)*Math.CInt(7, 2) /espacioMuestral);
		return 0;
	}

	
	public double calcularPromedioNumero() {
		int totalEstrella = (int) (euromillonesRepository.count());
		int totalNumeros = totalEstrella * 6;
		promedioNumeroDouble = new double[50];
		promedioEstrellaDouble = new double[12];

		for (int i = 0; i < aparicionesNumero.length; i++) {
			promedioNumeroDouble[i] = Math.redondeo(((double) aparicionesNumero[i] / (double) totalNumeros) * 100);
		}
		for (int i = 0; i < aparicionesEstrella.length; i++) {
			promedioEstrellaDouble[i] = Math.redondeo(((double) aparicionesEstrella[i] / (double) totalEstrella) * 100);
		}

		return promedioEstrellaDouble[0];
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
		esperanzaDouble=0.5;
		return esperanzaDouble;
	}

	
	public double calcularMediaResultados() {
		mediaResultadosDouble = 0;
		int total = 0;

		for (Euromillones p : euromillonesRepository.findAll()) {
			mediaResultadosDouble += p.getResultado0();
			mediaResultadosDouble += p.getResultado1();
			mediaResultadosDouble += p.getResultado2();
			mediaResultadosDouble += p.getResultado3();
			mediaResultadosDouble += p.getResultado4();
			total = total + 6;
		}
		mediaResultadosDouble = Math.redondeo(mediaResultadosDouble / total);
		return mediaResultadosDouble;
	}

	
	public double calcularMediaComplementos() {
		mediaComplementosDouble = 0;
		int total = 0;

		for (Euromillones p : euromillonesRepository.findAll()) {
			mediaComplementosDouble += p.getEstrella0();
			mediaComplementosDouble += p.getEstrella1();
			total = total + 2;
		}
		mediaComplementosDouble = Math.redondeo(mediaComplementosDouble / total);
		return mediaComplementosDouble;
	}

	
	public void calcularTodo() {
		calcularProbabilidadesVictoria();
		calcularApariciones();
		calcularPromedioNumero();
		calcularEsperanza();
		calcularMediaResultados();
		calcularMediaComplementos();
		
		probabilidad5y2=Math.doubleToString(probabilidad5y2Double);
		probabilidad5y1=Math.doubleToString(probabilidad5y1Double);
		probabilidad5=Math.doubleToString(probabilidad5Double);
		probabilidad4y2=Math.doubleToString(probabilidad4y2Double);
		probabilidad4y1=Math.doubleToString(probabilidad4y1Double);
		probabilidad3y2=Math.doubleToString(probabilidad3y2Double);
		probabilidad4=Math.doubleToString(probabilidad3y2Double);
		probabilidad2y2=Math.doubleToString(probabilidad2y2Double);
		probabilidad3y1=Math.doubleToString(probabilidad3y1Double);
		probabilidad3=Math.doubleToString(probabilidad3Double);
		probabilidad1y2=Math.doubleToString(probabilidad1y2Double);
		probabilidad2y1=Math.doubleToString(probabilidad2y1Double);
		probabilidad2=Math.doubleToString(probabilidad2Double);
		
		promedioNumero=new String[promedioNumeroDouble.length];
		int i =0;
		for(double d : promedioNumeroDouble) {
			promedioNumero[i]=Math.doubleToString(d);
			i++;
		}
		promedioEstrella=new String[promedioEstrellaDouble.length];
		i =0;
		for(double d : promedioEstrellaDouble) {
			promedioEstrella[i]=Math.doubleToString(d);
			i++;
		}

		esperanza=Math.doubleToString(esperanzaDouble);
		mediaResultados=Math.doubleToString(mediaResultadosDouble);
		mediaComplementos=Math.doubleToString(mediaComplementosDouble);
		
	}
}
