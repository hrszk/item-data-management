package com.example.itemdatamanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(InsertCategoryController.class);

    @GetMapping("/toPageAddCategory")
    public String toPageAddCategory() {
        return "category/add";
    }

    @PostMapping("/addCategory")
    public String addCategory(String name) {
        categoryService.insertParentCategory(name);
        logger.info("親カテゴリ {} が追加されました",name);
        return "redirect:/showCategoryList";
    }

    @PostMapping("/addChildCategory")
    public String addChildCategory(String parentCategory, String name) {
        Category category = new Category();
        category.setName(name);
        category.setParent(1);
        category.setNameAll(parentCategory + "/" + name);

        categoryService.insertCategory(category);
        logger.info("子カテゴリ {} が追加されました",name);
        return "redirect:/showCategoryList";
    }

    @PostMapping("/addGrandChild")
    public String addGrandChild(String parentCategory, String childCategory, String name) {
        Category category = new Category();
        category.setName(name);
        category.setParent(2);
        category.setNameAll(parentCategory + "/" + childCategory + "/" + name);

        categoryService.insertCategory(category);
        logger.info("孫カテゴリ {} が追加されました",name);
        return "redirect:/showCategoryList";
    }
}
