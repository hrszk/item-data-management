package com.example.itemdatamanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.itemdatamanagement.domain.ItemAndCategory;
import com.example.itemdatamanagement.repository.ItemAndCategoryRepository;

@Service
public class ItemAndCategoryService {

    @Autowired
    private ItemAndCategoryRepository itemAndCategoryRepository;

    public List<ItemAndCategory> searchItem(String name, String nameAll, String brand) {
        return itemAndCategoryRepository.searchItem(name, nameAll, brand);
    }
}
