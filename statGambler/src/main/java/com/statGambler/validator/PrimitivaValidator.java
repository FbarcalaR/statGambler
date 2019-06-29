package com.statGambler.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.statGambler.model.Primitiva;

@Component
public class PrimitivaValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Primitiva.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
    	Primitiva primitiva = (Primitiva) o;

        if (primitiva.getResultado0()<1 || primitiva.getResultado0()>49) {
            errors.rejectValue("resultado0", "Size.euromillones.resultadoEuromillones");
        }
        if (primitiva.getResultado1()<1|| primitiva.getResultado1()>49) {
            errors.rejectValue("resultado1", "Size.euromillones.resultadoEuromillones");
        }
        if (primitiva.getResultado2()<1|| primitiva.getResultado2()>49) {
            errors.rejectValue("resultado2", "Size.euromillones.resultadoEuromillones");
        }
        if (primitiva.getResultado3()<1|| primitiva.getResultado3()>49) {
            errors.rejectValue("resultado3", "Size.euromillones.resultadoEuromillones");
        }
        if (primitiva.getResultado4()<1|| primitiva.getResultado4()>49) {
            errors.rejectValue("resultado4", "Size.euromillones.resultadoEuromillones");
        }
        if (primitiva.getResultado5()<1|| primitiva.getResultado5()>49) {
            errors.rejectValue("resultado5", "Size.euromillones.resultadoEuromillones");
        }
        
        if (primitiva.getReintegro()<1|| primitiva.getReintegro()>9) {
            errors.rejectValue("reintegro", "Size.primitiva.reintegro");
        }
    }
}
