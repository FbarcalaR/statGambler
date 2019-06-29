package com.statGambler.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.statGambler.model.Ruleta;
import com.statGambler.services.MyUserDetailsService;

@Component
public class RuletaValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Ruleta.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
    	Ruleta ruleta = (Ruleta) o;

        if (ruleta.getNumero()<1 || ruleta.getNumero()>37) {
            errors.rejectValue("numero", "Size.euromillones.resultadoEuromillones");
        }
    }
}
