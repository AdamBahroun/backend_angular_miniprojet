package com.adam.voiture.mapper;

import com.adam.voiture.dto.MarqueDTO;
import com.adam.voiture.dto.VoitureDTO;
import com.adam.voiture.dto.VoitureRequestDTO;
import com.adam.voiture.entities.Marque;
import com.adam.voiture.entities.Voiture;
import org.springframework.stereotype.Component;

@Component
public class VoitureMapper {

    public VoitureDTO toDTO(Voiture voiture) {
        if (voiture == null)
            return null;
        VoitureDTO dto = new VoitureDTO();
        dto.setIdVoiture(voiture.getIdVoiture());
        dto.setNomVoiture(voiture.getNomVoiture());
        dto.setPrixVoiture(voiture.getPrixVoiture());
        dto.setDateCreation(voiture.getDateCreation());
        if (voiture.getMarque() != null) {
            dto.setIdMarque(voiture.getMarque().getIdmarque());
            dto.setNomMarque(voiture.getMarque().getNommarque());
        }
        return dto;
    }

    public Voiture toEntity(VoitureRequestDTO dto, Marque marque) {
        if (dto == null)
            return null;
        Voiture voiture = new Voiture();
        voiture.setNomVoiture(dto.getNomVoiture());
        voiture.setPrixVoiture(dto.getPrixVoiture());
        voiture.setDateCreation(dto.getDateCreation());
        voiture.setMarque(marque);
        return voiture;
    }

    public MarqueDTO toMarqueDTO(Marque marque) {
        if (marque == null)
            return null;
        MarqueDTO dto = new MarqueDTO();
        dto.setIdmarque(marque.getIdmarque());
        dto.setNommarque(marque.getNommarque());
        dto.setDescriptionmarque(marque.getDescriptionmarque());
        return dto;
    }
}