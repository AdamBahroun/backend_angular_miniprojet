package com.adam.voiture.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoitureRequestDTO {
    private String nomVoiture;
    private Double prixVoiture;
    private Date dateCreation;
    private Long idMarque;
}