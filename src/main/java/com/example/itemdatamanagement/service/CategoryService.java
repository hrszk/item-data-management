package com.example.itemdatamanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.itemdatamanagement.domain.Category;
import com.example.itemdatamanagement.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAllParentCategory() {
        return categoryRepository.findAllParentCategory();
    }

    public void deleteCategory(Integer id) {
        categoryRepository.deleteCategory(id);
    }
}
