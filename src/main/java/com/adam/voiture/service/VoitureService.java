package com.adam.voiture.service;

import java.util.List;

import com.adam.voiture.entities.Marque;
import com.adam.voiture.entities.Voiture;

public interface VoitureService {
	Voiture saveVoiture(Voiture p);
	Voiture updateVoiture(Voiture p);
	void deleteVoiture(Voiture p);
	void deleteVoitureById(Long id);
	Voiture getVoiture(Long id);
	List<Voiture> getAllVoitures();
	List<Voiture> findByNomVoiture(String nom);
	List<Voiture> findByNomVoitureContains(String nom);
	List<Voiture> findByNomPrix (String nom, Double prix);
	List<Voiture> findByMarque (Marque marque);
	List<Voiture> findByMarqueIdmarque(Long id);
	List<Voiture> findByOrderByNomVoitureAsc();
	List<Voiture> trierVoituresNomsPrix();

}
