package com.example.itemdatamanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.itemdatamanagement.domain.Image;
import com.example.itemdatamanagement.repository.ImageRepository;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public void insertImage(Image image) {
        imageRepository.insertImage(image);
    }
}
