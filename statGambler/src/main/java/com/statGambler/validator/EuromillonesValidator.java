package com.statGambler.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.statGambler.model.Euromillones;

@Component
public class EuromillonesValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Euromillones.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
    	Euromillones euromillones = (Euromillones) o;

        if (euromillones.getResultado0()<1 || euromillones.getResultado0()>50) {
            errors.rejectValue("resultado0", "Size.euromillones.resultadoEuromillones");
        }
        if (euromillones.getResultado1()<1|| euromillones.getResultado1()>50) {
            errors.rejectValue("resultado1", "Size.euromillones.resultadoEuromillones");
        }
        if (euromillones.getResultado2()<1|| euromillones.getResultado2()>50) {
            errors.rejectValue("resultado2", "Size.euromillones.resultadoEuromillones");
        }
        if (euromillones.getResultado3()<1|| euromillones.getResultado3()>50) {
            errors.rejectValue("resultado3", "Size.euromillones.resultadoEuromillones");
        }
        if (euromillones.getResultado4()<1|| euromillones.getResultado4()>50) {
            errors.rejectValue("resultado4", "Size.euromillones.resultadoEuromillones");
        }
        
        if (euromillones.getEstrella0()<1|| euromillones.getEstrella0()>50) {
            errors.rejectValue("estrella0", "Size.euromillones.estrellaEuromillones");
        }
        if (euromillones.getEstrella1()<1|| euromillones.getEstrella1()>50) {
            errors.rejectValue("estrella1", "Size.euromillones.estrellaEuromillones");
        }
        
    }
    
    
    
}
