package com.statGambler.services;

public interface GameService {
	void calcularTodo(double bote);
	
	double calcularProbabilidadesVictoria();
	
	double calcularPromedioNumero();
	
	double calcularApariciones();

	double setBote(double bote);
		
	double calcularEsperanza();

	double calcularMediaResultados();
	
	double calcularMediaComplementos();

}
