package com.statGambler.model;

import javax.persistence.*;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class EstadisticasPersonales{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    
    private double dineroGastadoPrimitivas;
    private double dineroGanadoPrimitivas;
    private double beneficioPrimitivas;
    private int apuestasPrimitivasJugadas;
    private double beneficioMedioPrimitivas;
    
    private double dineroGastadoEuromillones;
    private double dineroGanadoEuromillones;
    private double beneficioEuromillones;
    private int apuestasEuromillonesJugadas;
    private double beneficioMedioEuromillones;
    
    private double dineroGastadoRuletas;
    private double dineroGanadoRuletas;
    private double beneficioRuletas;
    private int apuestasRuletasJugadas;
    private double beneficioMedioRuletas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public double getDineroGastadoPrimitivas() {
		return dineroGastadoPrimitivas;
	}

	public void setDineroGastadoPrimitivas(double dineroGastadoPrimitivas) {
		this.dineroGastadoPrimitivas = dineroGastadoPrimitivas;
	}

	public double getDineroGanadoPrimitivas() {
		return dineroGanadoPrimitivas;
	}

	public void setDineroGanadoPrimitivas(double dineroGanadoPrimitivas) {
		this.dineroGanadoPrimitivas = dineroGanadoPrimitivas;
	}

	public double getBeneficioPrimitivas() {
		return beneficioPrimitivas;
	}

	public void setBeneficioPrimitivas(double beneficioPrimitivas) {
		this.beneficioPrimitivas = beneficioPrimitivas;
	}

	public int getApuestasPrimitivasJugadas() {
		return apuestasPrimitivasJugadas;
	}

	public void setApuestasPrimitivasJugadas(int apuestasPrimitivasJugadas) {
		this.apuestasPrimitivasJugadas = apuestasPrimitivasJugadas;
	}

	public double getBeneficioMedioPrimitivas() {
		return beneficioMedioPrimitivas;
	}

	public void setBeneficioMedioPrimitivas(double beneficioMedioPrimitivas) {
		this.beneficioMedioPrimitivas = beneficioMedioPrimitivas;
	}

	public double getDineroGastadoEuromillones() {
		return dineroGastadoEuromillones;
	}

	public void setDineroGastadoEuromillones(double dineroGastadoEuromillones) {
		this.dineroGastadoEuromillones = dineroGastadoEuromillones;
	}

	public double getDineroGanadoEuromillones() {
		return dineroGanadoEuromillones;
	}

	public void setDineroGanadoEuromillones(double dineroGanadoEuromillones) {
		this.dineroGanadoEuromillones = dineroGanadoEuromillones;
	}

	public double getBeneficioEuromillones() {
		return beneficioEuromillones;
	}

	public void setBeneficioEuromillones(double beneficioEuromillones) {
		this.beneficioEuromillones = beneficioEuromillones;
	}

	public int getApuestasEuromillonesJugadas() {
		return apuestasEuromillonesJugadas;
	}

	public void setApuestasEuromillonesJugadas(int apuestasEuromillonesJugadas) {
		this.apuestasEuromillonesJugadas = apuestasEuromillonesJugadas;
	}

	public double getBeneficioMedioEuromillones() {
		return beneficioMedioEuromillones;
	}

	public void setBeneficioMedioEuromillones(double beneficioMedioEuromillones) {
		this.beneficioMedioEuromillones = beneficioMedioEuromillones;
	}

	public double getDineroGastadoRuletas() {
		return dineroGastadoRuletas;
	}

	public void setDineroGastadoRuletas(double dineroGastadoRuletas) {
		this.dineroGastadoRuletas = dineroGastadoRuletas;
	}

	public double getDineroGanadoRuletas() {
		return dineroGanadoRuletas;
	}

	public void setDineroGanadoRuletas(double dineroGanadoRuletas) {
		this.dineroGanadoRuletas = dineroGanadoRuletas;
	}

	public double getBeneficioRuletas() {
		return beneficioRuletas;
	}

	public void setBeneficioRuletas(double beneficioRuletas) {
		this.beneficioRuletas = beneficioRuletas;
	}

	public int getApuestasRuletasJugadas() {
		return apuestasRuletasJugadas;
	}

	public void setApuestasRuletasJugadas(int apuestasRuletasJugadas) {
		this.apuestasRuletasJugadas = apuestasRuletasJugadas;
	}

	public double getBeneficioMedioRuletas() {
		return beneficioMedioRuletas;
	}

	public void setBeneficioMedioRuletas(double beneficioMedioRuletas) {
		this.beneficioMedioRuletas = beneficioMedioRuletas;
	}

}
