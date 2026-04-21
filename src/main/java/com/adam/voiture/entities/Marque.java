package com.adam.voiture.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Marque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idmarque;

    private String nommarque;
    private String descriptionmarque;

    @JsonIgnore
    @OneToMany(mappedBy = "marque")
    private List<Voiture> voitures;

    public Marque(String nommarque, String descriptionmarque, List<Voiture> voitures) {
        this.nommarque = nommarque;
        this.descriptionmarque = descriptionmarque;
        this.voitures = voitures;
    }
}