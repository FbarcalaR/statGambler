package com.statGambler.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.statGambler.Math.Math;
import com.statGambler.model.Euromillones;
import com.statGambler.model.Ruleta;
import com.statGambler.model.Ruleta;
import com.statGambler.repository.RuletaRepository;

@Service("RuletaService")
public class RuletaService{

	@Autowired
	RuletaRepository ruletaRepository;
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	public double probabilidadPlenoDouble;
	public double probabilidadCaballoDouble;
	public double probabilidadTransversalDouble;
	public double probabilidadFilaDobleDouble;
	public double probabilidadCuadroDouble;
	public double probabilidadDocenasDouble;
	public double probabilidadColumnasDouble;
	public double probabilidadColorDouble;
	public double probabilidadParImparDouble;
	public double probabilidadPasaFaltaDouble;
	public double probabilidad19_36Double;
	
	public double esperanzaPlenoDouble;
	public double esperanzaCaballoDouble;
	public double esperanzaTransversalDouble;
	public double esperanzaFilaDobleDouble;
	public double esperanzaCuadroDouble;
	public double esperanzaDocenasDouble;
	public double esperanzaColumnasDouble;
	public double esperanzaColorDouble;
	public double esperanzaParImparDouble;
	public double esperanzaPasaFaltaDouble;
	
	public double apuestaDouble;
	public double promedioNumeroDouble [];
	public int aparicionesNumero[];
	
	public String probabilidadPleno;
	public String probabilidadCaballo;
	public String probabilidadTransversal;
	public String probabilidadFilaDoble;
	public String probabilidadCuadro;
	public String probabilidadDocenas;
	public String probabilidadColumnas;
	public String probabilidadColor;
	public String probabilidadParImpar;
	public String probabilidadPasaFalta;
	public String probabilidad19_36;
	
	public String esperanzaPleno;
	public String esperanzaCaballo;
	public String esperanzaTransversal;
	public String esperanzaFilaDoble;
	public String esperanzaCuadro;
	public String esperanzaDocenas;
	public String esperanzaColumnas;
	public String esperanzaColor;
	public String esperanzaParImpar;
	public String esperanzaPasaFalta;
	
	public String apuesta;
	public String promedioNumero[];

	
	public double calcularProbabilidadesVictoria() {
		probabilidadPlenoDouble=Math.redondeo(1.0/37.0);
		probabilidadCaballoDouble=Math.redondeo(2.0/37.0);
		probabilidadTransversalDouble=Math.redondeo(3.0/37.0);
		probabilidadFilaDobleDouble=Math.redondeo(6.0/37.0);
		probabilidadCuadroDouble=Math.redondeo(4.0/37.0);
		probabilidadDocenasDouble=Math.redondeo(12.0/37.0);
		probabilidadColumnasDouble=Math.redondeo(12.0/37.0);
		probabilidadColorDouble=Math.redondeo(18.0/37.0);
		probabilidadParImparDouble=Math.redondeo(18.0/37.0);
		probabilidadPasaFaltaDouble=Math.redondeo(18.0/37.0);
		return probabilidadPlenoDouble;
	}

	
	public double calcularPromedioNumero() {
		int totalJugadas = (int) (ruletaRepository.count());
		promedioNumeroDouble = new double[36];

		for (int i = 0; i < promedioNumeroDouble.length; i++) {
			promedioNumeroDouble[i] = Math.redondeo(((double) aparicionesNumero[i] / (double) totalJugadas) * 100);
		}
		return promedioNumeroDouble[0];
	}

	
	public double calcularApariciones() {
		aparicionesNumero = new int[36];

		for (Ruleta r : ruletaRepository.findAll()) {
			if(r.getId()==myUserDetailsService.getUserPrincipal().getId())
			aparicionesNumero[r.getNumero() - 1]++;
		}
		return aparicionesNumero[0];
	}
	
	public double calcularEsperanza() {
		esperanzaPlenoDouble=Math.redondeo(probabilidadPlenoDouble*apuestaDouble*35+(1-probabilidadPlenoDouble)*(-apuestaDouble));
		esperanzaCaballoDouble=Math.redondeo(probabilidadCaballoDouble*apuestaDouble*17+(1-probabilidadCaballoDouble)*(-apuestaDouble));
		esperanzaTransversalDouble=Math.redondeo(probabilidadTransversalDouble*apuestaDouble*11+(1-probabilidadTransversalDouble)*(-apuestaDouble));
		esperanzaFilaDobleDouble=Math.redondeo(probabilidadFilaDobleDouble*apuestaDouble*5+(1-probabilidadFilaDobleDouble)*(-apuestaDouble));
		esperanzaCuadroDouble=Math.redondeo(probabilidadCuadroDouble*apuestaDouble*8+(1-probabilidadCuadroDouble)*(-apuestaDouble));
		esperanzaDocenasDouble=Math.redondeo(probabilidadDocenasDouble*apuestaDouble*2+(1-probabilidadDocenasDouble)*(-apuestaDouble));
		esperanzaColumnasDouble=Math.redondeo(probabilidadColumnasDouble*apuestaDouble*2+(1-probabilidadColumnasDouble)*(-apuestaDouble));
		esperanzaColorDouble=Math.redondeo(probabilidadColorDouble*apuestaDouble*1-(18.0/37.0)*apuestaDouble-(1.0/37.0*0.5)*apuestaDouble);
		esperanzaParImparDouble=Math.redondeo(probabilidadParImparDouble*apuestaDouble*1-(18.0/37.0)*apuestaDouble-(1.0/37.0*0.5)*apuestaDouble);
		esperanzaPasaFaltaDouble=Math.redondeo(probabilidadPasaFaltaDouble*apuestaDouble*1-(18.0/37.0)*apuestaDouble-(1.0/37.0*0.5)*apuestaDouble);
		
		return esperanzaPlenoDouble;
	}

	public void setApuesta(double apuesta) {
		this.apuestaDouble = apuesta;
	}
	
	public double getApuesta() {
		return apuestaDouble;
	}
	
	public void calcularTodo() {
		calcularProbabilidadesVictoria();
		calcularApariciones();
		calcularPromedioNumero();
		calcularEsperanza();
		
		probabilidadPleno=Math.doubleToString(probabilidadPlenoDouble);
		probabilidadCaballo=Math.doubleToString(probabilidadCaballoDouble);
		probabilidadTransversal=Math.doubleToString(probabilidadFilaDobleDouble);
		probabilidadFilaDoble=Math.doubleToString(probabilidadFilaDobleDouble);
		probabilidadCuadro=Math.doubleToString(probabilidadCuadroDouble);
		probabilidadDocenas=Math.doubleToString(probabilidadDocenasDouble);
		probabilidadColumnas=Math.doubleToString(probabilidadColumnasDouble);
		probabilidadColor=Math.doubleToString(probabilidadColorDouble);
		probabilidadParImpar=Math.doubleToString(probabilidadParImparDouble);
		probabilidadPasaFalta=Math.doubleToString(probabilidadPasaFaltaDouble);
		probabilidad19_36=Math.doubleToString(probabilidad19_36Double);
		esperanzaPleno=Math.doubleToString(esperanzaPlenoDouble);
		esperanzaCaballo=Math.doubleToString(esperanzaCaballoDouble);
		esperanzaTransversal=Math.doubleToString(esperanzaTransversalDouble);
		esperanzaFilaDoble=Math.doubleToString(esperanzaFilaDobleDouble);
		esperanzaCuadro=Math.doubleToString(esperanzaCuadroDouble);
		esperanzaDocenas=Math.doubleToString(esperanzaDocenasDouble);
		esperanzaColumnas=Math.doubleToString(esperanzaColumnasDouble);
		esperanzaColor=Math.doubleToString(esperanzaColorDouble);
		esperanzaParImpar=Math.doubleToString(esperanzaParImparDouble);
		esperanzaPasaFalta=Math.doubleToString(esperanzaPasaFaltaDouble);
		apuesta=Math.doubleToString(apuestaDouble);
		
		promedioNumero=new String[promedioNumeroDouble.length];
		int i =0;
		for(double d : promedioNumeroDouble) {
			promedioNumero[i]=Math.doubleToString(d);
			i++;
		}
	}
}
