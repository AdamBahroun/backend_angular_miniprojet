package com.adam.voiture.restcontrollers;

import com.adam.voiture.entities.Image;
import com.adam.voiture.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/image")
@CrossOrigin
public class ImageRestController {

    @Autowired
    ImageService imageService;

    @PostMapping("/upload/{idVoiture}")
    public Image uploadImage(@RequestParam("image") MultipartFile file,
            @PathVariable Long idVoiture) throws IOException {
        return imageService.uploadImage(file, idVoiture);
    }

    @GetMapping("/info/{id}")
    public Image getImageDetails(@PathVariable Long id) throws IOException {
        return imageService.getImageDetails(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) throws IOException {
        return imageService.getImage(id);
    }

    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
    }

    @GetMapping("/voiture/{idVoiture}")
    public List<Image> getImagesByVoiture(@PathVariable Long idVoiture) {
        return imageService.getImagesByVoiture(idVoiture);
    }
}