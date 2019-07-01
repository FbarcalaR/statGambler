package com.statGambler.services;

import java.util.Arrays;
import java.util.List;

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
	
	public int aparicionesRojo;
	public int aparicionesNegro;
	public int aparicionesVerde;
	public int aparicionesPar;
	public int aparicionesImpar;
	public int aparicionesDocena1;
	public int aparicionesDocena2;
	public int aparicionesDocena3;
	
	public double promedioRojoDouble;
	public double promedioNegroDouble;
	public double promedioVerdeDouble;
	public double promedioParDouble;
	public double promedioImparDouble;
	public double promedioDocena1Double;
	public double promedioDocena2Double;
	public double promedioDocena3Double;
	
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
	
	public String promedioRojo;
	public String promedioNegro;
	public String promedioVerde;
	public String promedioPar;
	public String promedioImpar;
	public String promedioDocena1;
	public String promedioDocena2;
	public String promedioDocena3;

	List<Integer> rojos=Arrays.asList(1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36);
	List<Integer> negros = Arrays.asList(2,4,6,8,10,11,13,17,20,22,24,26,28,29,31,33,35); 
	
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

	
	public double calcularPromedio() {
		int totalJugadas = (int) (ruletaRepository.count());
		promedioNumeroDouble = new double[36];

		for (int i = 0; i < promedioNumeroDouble.length; i++) {
			promedioNumeroDouble[i] = Math.redondeo(((double) aparicionesNumero[i] / (double) totalJugadas) * 100);
		}
		
		 promedioRojoDouble=Math.redondeo(((double)aparicionesRojo/ (double) totalJugadas) * 100);
		 promedioNegroDouble=Math.redondeo(((double)aparicionesNegro/ (double) totalJugadas) * 100);
		 promedioVerdeDouble=Math.redondeo(((double)aparicionesVerde/ (double) totalJugadas) * 100);
		 promedioParDouble=Math.redondeo(((double)aparicionesPar/ (double) totalJugadas) * 100);
		 promedioImparDouble=Math.redondeo(((double)aparicionesImpar/ (double) totalJugadas) * 100);
		 promedioDocena1Double=Math.redondeo(((double)aparicionesDocena1/ (double) totalJugadas) * 100);
		 promedioDocena2Double=Math.redondeo(((double)aparicionesDocena2/ (double) totalJugadas) * 100);
		 promedioDocena3Double=Math.redondeo(((double)aparicionesDocena3/ (double) totalJugadas) * 100);
		
		return promedioNumeroDouble[0];
	}

	
	public double calcularApariciones() {
		aparicionesNumero = new int[36];
		aparicionesRojo=0;
		aparicionesNegro=0;
		aparicionesVerde=0;
		aparicionesPar=0;
		aparicionesImpar=0;
		aparicionesDocena1=0;
		aparicionesDocena2=0;
		aparicionesDocena3=0;

		for (Ruleta r : ruletaRepository.findAll()) {
			if(r.getUserId()==myUserDetailsService.getUserPrincipal().getRuletaId()) {
				int numero=r.getNumero();
				aparicionesNumero[r.getNumero() - 1]++;
				
				if(getColor(numero)=="rojo") {
					aparicionesRojo++;
				}
				else if(getColor(numero)=="negro") {
					aparicionesNegro++;
				}
				else{
					aparicionesVerde++;
				}
				
				if(numero%2==0) {
					aparicionesPar++;
				}
				else {
					aparicionesImpar++;
				}
				
				if(0<numero && numero<=12) {
					aparicionesDocena1++;
				}
				else if(12<numero && numero<=24) {
					aparicionesDocena2++;
				}
				else if(24<numero && numero<=36) {
					aparicionesDocena3++;
				}
			}
		}
		
		return aparicionesNumero[0];
	}
	
	private String getColor(int numero) {
		
		if(rojos.contains(numero)) {
			return "rojo";
		}
		else if(negros.contains(numero)) {
			return "negro";
		}
		else
			return "verde";
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
		calcularPromedio();
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
		
		 promedioRojo=Math.doubleToString(promedioRojoDouble);
		 promedioNegro=Math.doubleToString(promedioNegroDouble);
		 promedioVerde=Math.doubleToString(promedioVerdeDouble);
		 promedioPar=Math.doubleToString(promedioParDouble);
		 promedioImpar=Math.doubleToString(promedioImparDouble);
		 promedioDocena1=Math.doubleToString(promedioDocena1Double);
		 promedioDocena2=Math.doubleToString(promedioDocena2Double);
		 promedioDocena3=Math.doubleToString(promedioDocena3Double);
		
		promedioNumero=new String[promedioNumeroDouble.length];
		int i =0;
		for(double d : promedioNumeroDouble) {
			promedioNumero[i]=Math.doubleToString(d);
			i++;
		}
	}
}
