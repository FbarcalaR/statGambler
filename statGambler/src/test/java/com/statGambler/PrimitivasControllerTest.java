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

import com.statGambler.controller.PrimitivaController;
import com.statGambler.model.Primitiva;
import com.statGambler.repository.PrimitivaRepository;

import static org.mockito.Mockito.*;

import java.util.Optional;

public class PrimitivasControllerTest {
	
	@InjectMocks
	private PrimitivaController primitivaController;

	@Mock
	private PrimitivaRepository primitivaRepository;
	
	@Mock
	private BindingResult bindingResult;
	
	@Mock
	private Model model;
	
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

	@Test
	public void testPrimitivaAdd() {
		Primitiva primitiva = new Primitiva();
		primitiva.setId(1l);
        when(model.addAttribute("primitiva", primitivaRepository.findAll())).thenReturn(model);
		
		
		String resul=primitivaController.addGame(primitiva, bindingResult, model);
		
		assertThat(resul, is("primitivas/primitivas"));
	}
	
	@Test
	public void testPrimitivaUpdate() {
		Primitiva primitiva = new Primitiva();
		primitiva.setId(1l);
        when(bindingResult.hasErrors()).thenReturn(true);
        when(model.addAttribute("primitiva", primitivaRepository.findAll())).thenReturn(model);
		
		
		String resul=primitivaController.updateGame(1l, primitiva, bindingResult, model);
		
		assertThat(resul, is("primitivas/update-primitiva"));
	}
	
	@Test
	public void testPrimitivaDelete() {
		Primitiva primitiva = new Primitiva();
		primitiva.setId(1l);
		Optional<Primitiva> euMock=Optional.of(primitiva);
		when(primitivaRepository.findById(1l)).thenReturn(euMock);
        when(bindingResult.hasErrors()).thenReturn(true);
        when(model.addAttribute("primitiva", primitivaRepository.findAll())).thenReturn(model);
		
		
		String resul=primitivaController.deleteGame(1l, model);
		
		assertThat(resul, is("primitivas/primitivas"));
	}
	
}
