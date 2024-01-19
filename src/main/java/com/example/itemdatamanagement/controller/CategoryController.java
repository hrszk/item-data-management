package com.example.itemdatamanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.itemdatamanagement.domain.Category;
import com.example.itemdatamanagement.service.CategoryService;

@Controller
@RequestMapping({ "", "/" })
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/findAllCategory")
    public String findAllCategory(Model model) {
        List<Category> parentCategoryList = categoryService.findAllParentCategory();
        model.addAttribute("parentCategoryList", parentCategoryList);

        List<Category> childCategoryList = categoryService.findAllChildCategory();
        model.addAttribute("childCategoryList", childCategoryList);

        return "category/list";
    }
}
