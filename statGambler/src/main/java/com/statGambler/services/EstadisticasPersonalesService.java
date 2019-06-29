package com.statGambler.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		EstadisticasPersonales estadisticasViejas=getEstadisticas();
		
		estadisticasNuevas.setId(estadisticasViejas.getId());
		estadisticasNuevas.setDineroGastadoEuromillones(estadisticasViejas.getDineroGastadoEuromillones()+estadisticasNuevas.getDineroGastadoEuromillones());
		estadisticasNuevas.setDineroGanadoEuromillones(estadisticasViejas.getDineroGanadoEuromillones()+estadisticasNuevas.getDineroGanadoEuromillones());
		estadisticasNuevas.setBeneficioEuromillones(estadisticasNuevas.getDineroGanadoEuromillones()-estadisticasNuevas.getDineroGastadoEuromillones());
		estadisticasNuevas.setApuestasEuromillonesJugadas(estadisticasViejas.getApuestasEuromillonesJugadas()+1);
		estadisticasNuevas.setBeneficioMedioEuromillones(estadisticasNuevas.getBeneficioEuromillones()/estadisticasNuevas.getApuestasEuromillonesJugadas());
		estadisticasPersonalesRepository.save(estadisticasNuevas);
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
		
		estadisticasNuevas.setId(estadisticasViejas.getId());
		estadisticasNuevas.setDineroGastadoPrimitivas(estadisticasViejas.getDineroGastadoPrimitivas()+estadisticasNuevas.getDineroGastadoPrimitivas());
		estadisticasNuevas.setDineroGanadoPrimitivas(estadisticasViejas.getDineroGanadoPrimitivas()+estadisticasNuevas.getDineroGanadoPrimitivas());
		estadisticasNuevas.setBeneficioPrimitivas(estadisticasNuevas.getDineroGanadoPrimitivas()-estadisticasNuevas.getDineroGastadoPrimitivas());
		estadisticasNuevas.setApuestasPrimitivasJugadas(estadisticasViejas.getApuestasPrimitivasJugadas()+1);
		estadisticasNuevas.setBeneficioMedioPrimitivas(estadisticasNuevas.getBeneficioPrimitivas()/estadisticasNuevas.getApuestasPrimitivasJugadas());
		estadisticasPersonalesRepository.save(estadisticasNuevas);
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
		
		estadisticasNuevas.setId(estadisticasViejas.getId());
		estadisticasNuevas.setDineroGastadoRuletas(estadisticasViejas.getDineroGastadoRuletas()+estadisticasNuevas.getDineroGastadoRuletas());
		estadisticasNuevas.setDineroGanadoRuletas(estadisticasViejas.getDineroGanadoRuletas()+estadisticasNuevas.getDineroGanadoRuletas());
		estadisticasNuevas.setBeneficioRuletas(estadisticasNuevas.getDineroGanadoRuletas()-estadisticasNuevas.getDineroGastadoRuletas());
		estadisticasNuevas.setApuestasRuletasJugadas(estadisticasViejas.getApuestasRuletasJugadas()+1);
		estadisticasNuevas.setBeneficioMedioRuletas(estadisticasNuevas.getBeneficioRuletas()/estadisticasNuevas.getApuestasRuletasJugadas());
		estadisticasPersonalesRepository.save(estadisticasNuevas);
	}
	
}
