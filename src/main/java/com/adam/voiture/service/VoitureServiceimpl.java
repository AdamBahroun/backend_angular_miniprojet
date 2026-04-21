package com.adam.voiture.service;

import java.util.List;

import com.adam.voiture.entities.Marque;
import com.adam.voiture.entities.Voiture;
import com.adam.voiture.repository.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoitureServiceimpl implements VoitureService {

	@Autowired
	VoitureRepository voitureRepository;

	@Override
	public Voiture saveVoiture(Voiture p) {
		return voitureRepository.save(p);
	}

	@Override
	public Voiture updateVoiture(Voiture p) {
		return voitureRepository.save(p);
	}

	@Override
	public void deleteVoiture(Voiture p) {
		voitureRepository.delete(p);
	}

	@Override
	public void deleteVoitureById(Long id) {
		voitureRepository.deleteById(id);
	}

	@Override
	public Voiture getVoiture(Long id) {
		// CORRECTION : .get() remplacé par .orElseThrow()
		// .get() lève NoSuchElementException sans message clair
		return voitureRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Voiture introuvable avec l'id : " + id));
	}

	@Override
	public List<Voiture> getAllVoitures() {
		return voitureRepository.findAll();
	}

	@Override
	public List<Voiture> findByNomVoiture(String nom) {
		return voitureRepository.findByNomVoiture(nom);
	}

	@Override
	public List<Voiture> findByNomVoitureContains(String nom) {
		return voitureRepository.findByNomVoitureContains(nom);
	}

	@Override
	public List<Voiture> findByNomPrix(String nom, Double prix) {
		return voitureRepository.findByNomPrix(nom, prix);
	}

	@Override
	public List<Voiture> findByMarque(Marque marque) {
		return voitureRepository.findByMarque(marque);
	}

	@Override
	public List<Voiture> findByMarqueIdmarque(Long id) {
		return voitureRepository.findByMarqueIdmarque(id);
	}

	@Override
	public List<Voiture> findByOrderByNomVoitureAsc() {
		return voitureRepository.findByOrderByNomVoitureAsc();
	}

	@Override
	public List<Voiture> trierVoituresNomsPrix() {
		return voitureRepository.trierVoituresNomsPrix();
	}
}