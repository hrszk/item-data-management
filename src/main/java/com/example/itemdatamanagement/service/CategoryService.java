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

    public List<Category> findAllChildCategory() {
        return categoryRepository.findAllChildCategory();
    }

    public List<Category> findAllGrandChild() {
        return categoryRepository.findAllGrandChild();
    }

    public void deleteCategory(Integer id) {
        categoryRepository.deleteCategory(id);
    }

    public Category findByNameCategory(String nameAll) {
        return categoryRepository.findByNameCategory(nameAll);
    }
}
