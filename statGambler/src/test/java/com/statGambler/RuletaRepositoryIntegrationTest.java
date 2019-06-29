package com.statGambler;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.statGambler.model.Euromillones;
import com.statGambler.model.Ruleta;
import com.statGambler.repository.RuletaRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RuletaRepositoryIntegrationTest {

	@Autowired
	RuletaRepository ruletaRepository;

	@After
	public void limpiaAntes() {
		ruletaRepository.deleteAll();
	}
	
	@After
	public void limpiaDespues() {
		ruletaRepository.deleteAll();
	}

	@Test
	public void testSave() {
		long ruletasAntes = ruletaRepository.count();
		
		ruletaRepository.save(new Ruleta());
		long ruletasDespues = ruletaRepository.count();
		assertThat(ruletasAntes+1, equalTo(ruletasDespues));
	}
	
	@Test
	public void testFindAll() {
		ruletaRepository.save(new Ruleta());
		List<Ruleta> ruletas = (List<Ruleta>) ruletaRepository.findAll();
		assertThat(ruletas.size(), is(greaterThanOrEqualTo(0)));
	}
	
	@Test
	public void testDelete() {
		Ruleta p=new Ruleta();
		p.setId(1l);
		ruletaRepository.save(p);
		
		ruletaRepository.delete(p);
		long ruletaDespues = ruletaRepository.count();
		
		assertThat(1l, equalTo(ruletaDespues));
	}
	
}
