package com.example.itemdatamanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.itemdatamanagement.domain.Category;
import com.example.itemdatamanagement.service.CategoryService;

@Controller
@RequestMapping({ "", "/" })
public class InsertCategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/toPageAddCategory")
    public String toPageAddCategory() {
        return "category/add";
    }

    @PostMapping("/addCategory")
    public String addCategory(String name) {
        categoryService.insertParentCategory(name);
        return "redirect:/findAllCategory";
    }

    @PostMapping("/addChildCategory")
    public String addChildCategory(String parentCategory, String name) {
        Category category = new Category();
        category.setName(name);
        category.setParentId(1);
        category.setNameAll(parentCategory + "/" + name);

        categoryService.insertCategory(category);
        return "redirect:/findAllCategory";
    }

    @PostMapping("/addGrandChild")
    public String addGrandChild(String parentCategory, String childCategory, String name) {
        Category category = new Category();
        category.setName(name);
        category.setParentId(2);
        category.setNameAll(parentCategory + "/" + childCategory + "/" + name);

        categoryService.insertCategory(category);
        return "redirect:/findAllCategory";
    }
}
