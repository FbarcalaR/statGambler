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

import com.statGambler.controller.EuromillonesController;
import com.statGambler.model.Euromillones;
import com.statGambler.repository.EuromillonesRepository;

import static org.mockito.Mockito.*;

import java.util.Optional;

public class EuromillonesControllerTest {
	
	@InjectMocks
	private EuromillonesController euromillonesController;

	@Mock
	private EuromillonesRepository euromillonesRepository;
	
	@Mock
	private BindingResult bindingResult;
	
	@Mock
	private Model model;
	
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testEuromillonesAdd() {
		Euromillones euromillones = new Euromillones();
		euromillones.setId(1l);
        when(model.addAttribute("euromillones", euromillonesRepository.findAll())).thenReturn(model);
		
		
		String resul=euromillonesController.addGame(euromillones, bindingResult, model);
		
		assertThat(resul, is("euromillones/euromillones"));
	}
	
	@Test
	public void testEuromillonesUpdate() {
		Euromillones euromillones = new Euromillones();
		euromillones.setId(1l);
        when(bindingResult.hasErrors()).thenReturn(true);
        when(model.addAttribute("euromillones", euromillonesRepository.findAll())).thenReturn(model);
		
		
		String resul=euromillonesController.updateGame(1l, euromillones, bindingResult, model);
		
		assertThat(resul, is("euromillones/update-euromillones"));
	}
	
	@Test
	public void testEuromillonesDelete() {
		Euromillones euromillones = new Euromillones();
		euromillones.setId(1l);
		Optional<Euromillones> euMock=Optional.of(euromillones);
		when(euromillonesRepository.findById(1l)).thenReturn(euMock);
        when(bindingResult.hasErrors()).thenReturn(true);
        when(model.addAttribute("euromillones", euromillonesRepository.findAll())).thenReturn(model);
		
		
		String resul=euromillonesController.deleteGame(1l, model);
		
		assertThat(resul, is("euromillones/euromillones"));
	}
	
}
