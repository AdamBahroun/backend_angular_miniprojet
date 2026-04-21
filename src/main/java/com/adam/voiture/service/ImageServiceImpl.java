package com.adam.voiture.service;

import com.adam.voiture.entities.Image;
import com.adam.voiture.entities.Voiture;
import com.adam.voiture.repository.ImageRepository;
import com.adam.voiture.repository.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;
    @Autowired
    VoitureRepository voitureRepository;

    @Override
    public Image uploadImage(MultipartFile file, Long idVoiture) throws IOException {
        Voiture voiture = voitureRepository.findById(idVoiture)
                .orElseThrow(() -> new RuntimeException("Voiture introuvable : " + idVoiture));
        return imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(file.getBytes())
                .voiture(voiture)
                .build());
    }

    @Override
    public Image getImageDetails(Long id) throws IOException {
        Optional<Image> dbImage = imageRepository.findById(id);
        return Image.builder()
                .idImage(dbImage.get().getIdImage())
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .image(dbImage.get().getImage())
                .build();
    }

    @Override
    public ResponseEntity<byte[]> getImage(Long id) throws IOException {
        Optional<Image> dbImage = imageRepository.findById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(dbImage.get().getImage());
    }

    @Override
    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public List<Image> getImagesByVoiture(Long idVoiture) {
        Voiture voiture = voitureRepository.findById(idVoiture)
                .orElseThrow(() -> new RuntimeException("Voiture introuvable : " + idVoiture));
        return voiture.getImages();
    }
}