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
import com.statGambler.repository.EuromillonesRepository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EuromillonesRepositoryIntegrationTest {

	@Autowired
	private EuromillonesRepository euromillonesRepository;

	@After
	public void limpiaAntes() {
		euromillonesRepository.deleteAll();
	}
	
	@After
	public void limpiaDespues() {
		euromillonesRepository.deleteAll();
	}
	
	@Test
	public void testSave() {
		long euromillonesAntes = euromillonesRepository.count();
		
		euromillonesRepository.save(new Euromillones());
		long euromillonesDespues = euromillonesRepository.count();
		assertThat(euromillonesAntes+1, equalTo(euromillonesDespues));
	}
	
	@Test
	public void testFindAll() {
		euromillonesRepository.save(new Euromillones());
		List<Euromillones> euromillones = (List<Euromillones>) euromillonesRepository.findAll();
		assertThat(euromillones.size(), is(greaterThanOrEqualTo(0)));
	}
	
	
	@Test
	public void testDelete() {
		Euromillones p=new Euromillones();
		p.setId(1l);
		euromillonesRepository.save(p);
		
		euromillonesRepository.delete(p);
		long euromillonesDespues = euromillonesRepository.count();
		
		assertThat(1l, equalTo(euromillonesDespues));
	}
	
	
	
}
