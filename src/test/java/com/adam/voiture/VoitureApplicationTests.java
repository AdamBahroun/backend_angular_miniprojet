package com.adam.voiture;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.adam.voiture.entities.Marque;
import com.adam.voiture.entities.Voiture;
import com.adam.voiture.repository.VoitureRepository;

@SpringBootTest
class VoitureApplicationTests {
	
	@Autowired
	private VoitureRepository voitureRepository;
	@Test
	public void testCreatevoiture() {
	Voiture voit = new Voiture("BMW M5",203000.0,new Date());
	voitureRepository.save(voit);
	}
	@Test
	public void testFindVoiture()
	{
	Voiture p = voitureRepository.findById(1L).get();
	System.out.println(p);
	}
	
	@Test
	public void testUpdateVoiture()
	{
	Voiture p = voitureRepository.findById(1L).get();
	p.setPrixVoiture(1000.0);
	voitureRepository.save(p);
	System.out.println(p);
	}
	@Test
	public void testDeleteVoiture()
	{
	voitureRepository.deleteById(1L);;
	}

	@Test
	public void testListerTousVoitures()
	{
	List<Voiture> voite = voitureRepository.findAll();
	for (Voiture p : voite)
	{
	System.out.println(p);
	}
	}
	@Test
	public void testFindVoitureByNom()
	{
	List<Voiture> voite = voitureRepository.findByNomVoiture("Porsche 911");
	for (Voiture p : voite)
	{
	System.out.println(p);
	}}
	@Test
	public void testFindByNomVoitureContains()
	{
	List<Voiture> voite = voitureRepository.findByNomVoitureContains("911");
	for (Voiture p : voite)
	{
	System.out.println(p);
	}
	}
	@Test
	public void testfindByNomPrix()
	{
	List<Voiture> prods = voitureRepository.findByNomPrix("Porsche 911",1000.0);
	for (Voiture p : prods)
	{
	System.out.println(p);
	}
	}
	@Test
	public void testfindByMarque()
	{
	Marque marque = new Marque();
	marque.setIdmarque(1L);
	List<Voiture> prods = voitureRepository.findByMarque(marque);
	for (Voiture p : prods)
	{
	System.out.println(p);
	}
	}
	@Test
	public void FindByMarqueIdMarque()
	{
	List<Voiture> prods = voitureRepository.findByMarqueIdmarque(1L);
	for (Voiture p : prods)
	{
	System.out.println(p);
	}
	 }
	@Test
	public void testfindByOrderByNomVoitureAsc()
	{
	List<Voiture> prods =voitureRepository.findByOrderByNomVoitureAsc();
	for (Voiture p : prods)
	{
	System.out.println(p);
	}
	}
	@Test
	public void testTrierVoituresNomsPrix()
	{
	List<Voiture> prods = voitureRepository.trierVoituresNomsPrix();
	for (Voiture p : prods)
	{
	System.out.println(p);
	}
	}



}
