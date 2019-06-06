package com.statGambler;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.statGambler.controller.RuletaController;
import com.statGambler.model.Ruleta;
import com.statGambler.model.Ruleta;
import com.statGambler.repository.RuletaRepository;

import static org.mockito.Mockito.*;

import java.util.Optional;

public class RuletasControllerTest {
	
	@InjectMocks
	private RuletaController ruletaController;

	@Mock
	private RuletaRepository ruletaRepository;
	
	@Mock
	private BindingResult bindingResult;
	
	@Mock
	private Model model;
	
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

	@Test
	public void testRuletaAdd() {
		Ruleta ruleta = new Ruleta();
		ruleta.setId(1l);
        when(model.addAttribute("ruleta", ruletaRepository.findAll())).thenReturn(model);
		
		
		String resul=ruletaController.addGame(ruleta, bindingResult, model);
		
		assertThat(resul, is("ruletas/ruletas"));
	}
	
	@Test
	public void testRuletaUpdate() {
		Ruleta ruleta = new Ruleta();
		ruleta.setId(1l);
        when(bindingResult.hasErrors()).thenReturn(true);
        when(model.addAttribute("ruleta", ruletaRepository.findAll())).thenReturn(model);
		
		
		String resul=ruletaController.updateGame(1l, ruleta, bindingResult, model);
		
		assertThat(resul, is("ruletas/update-ruleta"));
	}
	
	@Test
	public void testRuletaDelete() {
		Ruleta ruleta = new Ruleta();
		ruleta.setId(1l);
		Optional<Ruleta> euMock=Optional.of(ruleta);
		when(ruletaRepository.findById(1l)).thenReturn(euMock);
        when(bindingResult.hasErrors()).thenReturn(true);
        when(model.addAttribute("ruleta", ruletaRepository.findAll())).thenReturn(model);
		
		
		String resul=ruletaController.deleteGame(1l, model);
		
		assertThat(resul, is("ruletas/ruletas"));
	}
}
