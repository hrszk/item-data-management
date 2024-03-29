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

    public Category findByIdCategory(Integer id) {
        return categoryRepository.findByIdCategory(id);
    }

    public List<Category> findByParentCategory(String nameAll) {
        return categoryRepository.findByParentCategory(nameAll);
    }

    public void insertParentCategory(String name) {
        categoryRepository.insertParentCategory(name);
    }

    public void insertCategory(Category category) {
        categoryRepository.insertCategory(category);
    }

    public void updateChildCategoryAndGrandChild(String name, String nameAll, Integer id) {
        categoryRepository.updateChildCategoryAndGrandChild(name, nameAll, id);
    }

    public Category findCategoryByNameAllAndParent(String nameAll, Integer parent) {
        return categoryRepository.findCategoryByNameAllAndParent(nameAll, parent);
    }

    public void updateCategory(Category category) {
        categoryRepository.updateCategory(category);
    }

    public List<Category> findCategoryByNameAll(String nameAll) {
        return categoryRepository.findCategoryByNameAll(nameAll);
    }

    public void updateNameAll(String nameAll, Integer id) {
        categoryRepository.updateNameAll(nameAll, id);
    }

    public void deleteCategoryByNameAll(String nameAll) {
        categoryRepository.deleteCategoryByNameAll(nameAll);
    }

    public void deleteParentCategory(String name) {
        categoryRepository.deleteParentCategory(name);
    }
}
