package com.example.itemdatamanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.itemdatamanagement.domain.Original;
import com.example.itemdatamanagement.repository.OriginalRepository;

@Service
public class OriginalService {

    @Autowired
    private OriginalRepository originalRepository;

    public void insertOriginal(Original original) {
        originalRepository.insertOriginal(original);
    }

}
