package com.example.demo.controller;

import com.example.demo.entity.ImageEntity;
import com.example.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            byte[] imageData = file.getBytes();
            ImageEntity savedImage = imageService.saveImage(imageData);
            return ResponseEntity.ok("Image uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error uploading image");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable Long id) {
        ImageEntity imageEntity = imageService.getImageById(id);

        if (imageEntity != null) {
            return ResponseEntity.ok(imageEntity.getImageData());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
