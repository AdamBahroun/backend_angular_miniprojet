package com.adam.voiture.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoitureDTO {
    private Long idVoiture;
    private String nomVoiture;
    private Double prixVoiture;
    private Date dateCreation;
    // On aplatit la relation : plus besoin d'envoyer l'objet Marque entier
    private Long idMarque;
    private String nomMarque;
}