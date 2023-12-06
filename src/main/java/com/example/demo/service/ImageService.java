package com.example.demo.service;

import com.example.demo.entity.ImageEntity;
import com.example.demo.repo.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public ImageEntity saveImage(byte[] imageData) {
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setImageData(imageData);
        return imageRepository.save(imageEntity);
    }

    public ImageEntity getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }
}
