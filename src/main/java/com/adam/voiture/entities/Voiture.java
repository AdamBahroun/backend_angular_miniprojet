package com.adam.voiture.entities;

import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class Voiture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVoiture;
    private String nomVoiture;
    private Double prixVoiture;
    private Date dateCreation;

    @ManyToOne
    @JsonIgnoreProperties({ "voitures" })
    private Marque marque;

    @OneToMany(mappedBy = "voiture", cascade = CascadeType.ALL)
    private List<Image> images;

    public Voiture() {
    }

    public Voiture(String nomVoiture, Double prixVoiture, Date dateCreation) {
        this.nomVoiture = nomVoiture;
        this.prixVoiture = prixVoiture;
        this.dateCreation = dateCreation;
    }

    public Long getIdVoiture() {
        return idVoiture;
    }

    public void setIdVoiture(Long idVoiture) {
        this.idVoiture = idVoiture;
    }

    public String getNomVoiture() {
        return nomVoiture;
    }

    public void setNomVoiture(String nomVoiture) {
        this.nomVoiture = nomVoiture;
    }

    public Double getPrixVoiture() {
        return prixVoiture;
    }

    public void setPrixVoiture(Double prixVoiture) {
        this.prixVoiture = prixVoiture;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}