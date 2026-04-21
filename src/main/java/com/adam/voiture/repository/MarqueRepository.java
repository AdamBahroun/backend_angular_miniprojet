package com.adam.voiture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.adam.voiture.entities.Marque;

@RepositoryRestResource(path = "marque")
@CrossOrigin("http://localhost:4200/")
public interface MarqueRepository  extends JpaRepository<Marque, Long> {

}