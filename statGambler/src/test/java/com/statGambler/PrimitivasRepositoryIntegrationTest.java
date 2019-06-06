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
import com.statGambler.model.Primitiva;
import com.statGambler.repository.PrimitivaRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PrimitivasRepositoryIntegrationTest {

	@Autowired
	private PrimitivaRepository primitivaRepository;

	@After
	public void limpiaAntes() {
		primitivaRepository.deleteAll();
	}
	
	@After
	public void limpiaDespues() {
		primitivaRepository.deleteAll();
	}

	@Test
	public void testSave() {
		long primitivasAntes = primitivaRepository.count();
		
		primitivaRepository.save(new Primitiva());
		long primitivasDespues = primitivaRepository.count();
		assertThat(primitivasAntes+1, equalTo(primitivasDespues));
	}
	
	@Test
	public void testFindAll() {
		primitivaRepository.save(new Primitiva());
		List<Primitiva> primitivas = (List<Primitiva>) primitivaRepository.findAll();
		assertThat(primitivas.size(), is(greaterThanOrEqualTo(0)));
	}
	
	@Test
	public void testDelete() {
		Primitiva p=new Primitiva();
		p.setId(1l);
		primitivaRepository.save(p);
		
		primitivaRepository.delete(p);
		long primitivasDespues = primitivaRepository.count();
		
		assertThat(1l, equalTo(primitivasDespues));
	}
	
}
