package com.statGambler.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.statGambler.Math.Math;
import com.statGambler.model.EstadisticasPersonales;
import com.statGambler.model.User;
import com.statGambler.repository.EstadisticasPersonalesRepository;

@Service
public class EstadisticasPersonalesService {
	
	@Autowired
	EstadisticasPersonalesRepository estadisticasPersonalesRepository;
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	public EstadisticasPersonales getEstadisticas() {
		User user=myUserDetailsService.getUserPrincipal();
		Long estadisticasID=user.getEstadisticasPersonalesId();
		if(estadisticasID!=null) {
			Optional<EstadisticasPersonales> estadisticas=estadisticasPersonalesRepository.findById(estadisticasID);
			return estadisticas.get();
		}
		else {
			EstadisticasPersonales e=estadisticasPersonalesRepository.save(new EstadisticasPersonales());
			user.setEstadisticasPersonalesId(e.getId());
			return getEstadisticas();
		}
		
	}
	
	public void setEstadisticasEuromillones(EstadisticasPersonales estadisticasNuevas) {
		EstadisticasPersonales estadisticasViejas=getEstadisticas();
		
		estadisticasNuevas.setId(estadisticasViejas.getId());
		estadisticasNuevas.setBeneficioEuromillones(estadisticasNuevas.getDineroGanadoEuromillones()-estadisticasNuevas.getDineroGastadoEuromillones());
		estadisticasNuevas.setBeneficioMedioEuromillones(estadisticasNuevas.getBeneficioEuromillones()/estadisticasNuevas.getApuestasEuromillonesJugadas());
		estadisticasPersonalesRepository.save(estadisticasNuevas);
	}
	
	public void setApuestaEuromillones(EstadisticasPersonales estadisticasNuevas) {
		System.out.println("estadistica nuevas euromillo: "+ estadisticasNuevas.getDineroGanadoEuromillones()+ " "+estadisticasNuevas.getDineroGastadoEuromillones());
		System.out.println("estadistica nuevas prim: "+ estadisticasNuevas.getDineroGanadoPrimitivas()+ " "+estadisticasNuevas.getDineroGastadoPrimitivas());
		
		EstadisticasPersonales estadisticasViejas=getEstadisticas();
		
		estadisticasViejas.setId(estadisticasViejas.getId());
		estadisticasViejas.setDineroGastadoEuromillones(estadisticasViejas.getDineroGastadoEuromillones()+estadisticasNuevas.getDineroGastadoEuromillones());
		estadisticasViejas.setDineroGanadoEuromillones(estadisticasViejas.getDineroGanadoEuromillones()+estadisticasNuevas.getDineroGanadoEuromillones());
		estadisticasViejas.setBeneficioEuromillones(estadisticasViejas.getDineroGanadoEuromillones()-estadisticasViejas.getDineroGastadoEuromillones());
		estadisticasViejas.setApuestasEuromillonesJugadas(estadisticasViejas.getApuestasEuromillonesJugadas()+1);
		estadisticasViejas.setBeneficioMedioEuromillones(Math.redondeoPromedios(estadisticasViejas.getBeneficioEuromillones()/estadisticasViejas.getApuestasEuromillonesJugadas()));
		
		estadisticasPersonalesRepository.save(estadisticasViejas);
	}

	public void setEstadisticasPrimitivas(EstadisticasPersonales estadisticasNuevas) {
		EstadisticasPersonales estadisticasViejas=getEstadisticas();
		
		estadisticasNuevas.setId(estadisticasViejas.getId());
		estadisticasNuevas.setBeneficioPrimitivas(estadisticasNuevas.getDineroGanadoPrimitivas()-estadisticasNuevas.getDineroGastadoPrimitivas());
		estadisticasNuevas.setBeneficioMedioPrimitivas(estadisticasNuevas.getBeneficioPrimitivas()/estadisticasNuevas.getApuestasPrimitivasJugadas());
		estadisticasPersonalesRepository.save(estadisticasNuevas);
	}
	
	public void setApuestaPrimitivas(EstadisticasPersonales estadisticasNuevas) {
		EstadisticasPersonales estadisticasViejas=getEstadisticas();
		
		estadisticasViejas.setId(estadisticasViejas.getId());
		estadisticasViejas.setDineroGastadoPrimitivas(estadisticasViejas.getDineroGastadoPrimitivas()+estadisticasNuevas.getDineroGastadoPrimitivas());
		estadisticasViejas.setDineroGanadoPrimitivas(estadisticasViejas.getDineroGanadoPrimitivas()+estadisticasNuevas.getDineroGanadoPrimitivas());
		estadisticasViejas.setBeneficioPrimitivas(estadisticasViejas.getDineroGanadoPrimitivas()-estadisticasViejas.getDineroGastadoPrimitivas());
		estadisticasViejas.setApuestasPrimitivasJugadas(estadisticasViejas.getApuestasPrimitivasJugadas()+1);
		estadisticasViejas.setBeneficioMedioPrimitivas(Math.redondeoPromedios(estadisticasViejas.getBeneficioPrimitivas()/estadisticasViejas.getApuestasPrimitivasJugadas()));
		estadisticasPersonalesRepository.save(estadisticasViejas);
	}

	public void setEstadisticasRuletas(EstadisticasPersonales estadisticasNuevas) {
		EstadisticasPersonales estadisticasViejas=getEstadisticas();
		
		estadisticasNuevas.setId(estadisticasViejas.getId());
		estadisticasNuevas.setBeneficioRuletas(estadisticasNuevas.getDineroGanadoRuletas()-estadisticasNuevas.getDineroGastadoRuletas());
		estadisticasNuevas.setBeneficioMedioRuletas(estadisticasNuevas.getBeneficioRuletas()/estadisticasNuevas.getApuestasRuletasJugadas());
		estadisticasPersonalesRepository.save(estadisticasNuevas);
	}
	
	public void setApuestaRuletas(EstadisticasPersonales estadisticasNuevas) {
		EstadisticasPersonales estadisticasViejas=getEstadisticas();
		
		estadisticasViejas.setId(estadisticasViejas.getId());
		estadisticasViejas.setDineroGastadoRuletas(estadisticasViejas.getDineroGastadoRuletas()+estadisticasNuevas.getDineroGastadoRuletas());
		estadisticasViejas.setDineroGanadoRuletas(estadisticasViejas.getDineroGanadoRuletas()+estadisticasNuevas.getDineroGanadoRuletas());
		estadisticasViejas.setBeneficioRuletas(estadisticasViejas.getDineroGanadoRuletas()-estadisticasViejas.getDineroGastadoRuletas());
		estadisticasViejas.setApuestasRuletasJugadas(estadisticasViejas.getApuestasRuletasJugadas()+1);
		estadisticasViejas.setBeneficioMedioRuletas(Math.redondeoPromedios(estadisticasViejas.getBeneficioRuletas()/estadisticasViejas.getApuestasRuletasJugadas()));
		estadisticasPersonalesRepository.save(estadisticasViejas);
	}
	
}
