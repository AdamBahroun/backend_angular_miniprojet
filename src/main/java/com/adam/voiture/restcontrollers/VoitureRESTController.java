package com.adam.voiture.restcontrollers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.adam.voiture.dto.VoitureDTO;
import com.adam.voiture.dto.VoitureRequestDTO;
import com.adam.voiture.entities.Marque;
import com.adam.voiture.entities.Voiture;
import com.adam.voiture.mapper.VoitureMapper;
import com.adam.voiture.repository.MarqueRepository;
import com.adam.voiture.service.VoitureService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class VoitureRESTController {

    @Autowired
    VoitureService voitureservice;

    @Autowired
    MarqueRepository marqueRepository;

    @Autowired
    VoitureMapper voitureMapper;

    // GET /api — retourne une liste de VoitureDTO
    @GetMapping()
    public List<VoitureDTO> getAllVoitures() {
        return voitureservice.getAllVoitures()
                .stream()
                .map(voitureMapper::toDTO)
                .collect(Collectors.toList());
    }

    // GET /api/{id}
    @GetMapping("/{id}")
    public ResponseEntity<VoitureDTO> getVoitureById(@PathVariable Long id) {
        Voiture voiture = voitureservice.getVoiture(id);
        return ResponseEntity.ok(voitureMapper.toDTO(voiture));
    }

    // POST /api — reçoit VoitureRequestDTO, retourne VoitureDTO
    @PostMapping()
    public ResponseEntity<VoitureDTO> createVoiture(@RequestBody VoitureRequestDTO requestDTO) {
        Marque marque = marqueRepository.findById(requestDTO.getIdMarque())
                .orElseThrow(() -> new RuntimeException("Marque introuvable : " + requestDTO.getIdMarque()));
        Voiture voiture = voitureMapper.toEntity(requestDTO, marque);
        Voiture saved = voitureservice.saveVoiture(voiture);
        return ResponseEntity.ok(voitureMapper.toDTO(saved));
    }

    // PUT /api — reçoit VoitureRequestDTO + id dans le body
    @PutMapping("/{id}")
    public ResponseEntity<VoitureDTO> updateVoiture(@PathVariable Long id,
            @RequestBody VoitureRequestDTO requestDTO) {
        Voiture existing = voitureservice.getVoiture(id);
        Marque marque = marqueRepository.findById(requestDTO.getIdMarque())
                .orElseThrow(() -> new RuntimeException("Marque introuvable : " + requestDTO.getIdMarque()));
        existing.setNomVoiture(requestDTO.getNomVoiture());
        existing.setPrixVoiture(requestDTO.getPrixVoiture());
        existing.setDateCreation(requestDTO.getDateCreation());
        existing.setMarque(marque);
        Voiture updated = voitureservice.updateVoiture(existing);
        return ResponseEntity.ok(voitureMapper.toDTO(updated));
    }

    // DELETE /api/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoiture(@PathVariable Long id) {
        voitureservice.deleteVoitureById(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/marque/{idmarque}
    @GetMapping("/marque/{idmarque}")
    public List<VoitureDTO> getVoituresByMarqueId(@PathVariable Long idmarque) {
        return voitureservice.findByMarqueIdmarque(idmarque)
                .stream()
                .map(voitureMapper::toDTO)
                .collect(Collectors.toList());
    }

    // GET /api/voitsByName/{nom}
    @GetMapping("/voitsByName/{nom}")
    public List<VoitureDTO> getVoituresByNom(@PathVariable String nom) {
        return voitureservice.findByNomVoitureContains(nom)
                .stream()
                .map(voitureMapper::toDTO)
                .collect(Collectors.toList());
    }
}