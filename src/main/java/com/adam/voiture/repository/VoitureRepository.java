package com.adam.voiture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.adam.voiture.entities.Marque;
import com.adam.voiture.entities.Voiture;
@RepositoryRestResource(path = "rest")
public interface VoitureRepository extends JpaRepository<Voiture,Long> {
	
	 List<Voiture> findByNomVoiture(String nom);;
	 List<Voiture> findByNomVoitureContains(String nom); 
	 
	 /*@Query("select p from Voiture p where p.nomVoiture like %?1 and p.prixVoiture > ?2")
	 List<Voiture> findByNomPrix (String nom, Double prix);*/
	 
	 @Query("select p from Voiture p where p.nomVoiture like %:nom and p.prixVoiture > :prix")
	 List<Voiture> findByNomPrix (@Param("nom") String nom,@Param("prix") Double prix);

	 @Query("select p from Voiture p where p.marque = ?1")
     List<Voiture> findByMarque (Marque marque);    

	 List<Voiture> findByMarqueIdmarque(Long id);
	 
	 List<Voiture> findByOrderByNomVoitureAsc();
	 
	 @Query("select p from Voiture p order by p.nomVoiture ASC, p.prixVoiture DESC")
	 List<Voiture> trierVoituresNomsPrix ();




}