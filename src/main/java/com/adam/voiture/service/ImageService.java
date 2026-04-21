package com.adam.voiture.service;

import com.adam.voiture.entities.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface ImageService {
    Image uploadImage(MultipartFile file, Long idVoiture) throws IOException;

    Image getImageDetails(Long id) throws IOException;

    ResponseEntity<byte[]> getImage(Long id) throws IOException;

    void deleteImage(Long id);

    List<Image> getImagesByVoiture(Long idVoiture);
}