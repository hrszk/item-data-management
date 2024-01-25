package com.example.itemdatamanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.itemdatamanagement.domain.Category;
import com.example.itemdatamanagement.domain.ItemAndCategory;
import com.example.itemdatamanagement.service.CategoryService;
import com.example.itemdatamanagement.service.ItemAndCategoryService;

@Controller
@RequestMapping({ "", "/" })
public class DeleteCategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ItemAndCategoryService itemAndCategoryService;

    @PostMapping("/deleteChildCategory")
    public String deleteChildCategory(String parentCategory, String childCategory,
            RedirectAttributes redirectAttributes) {
        String nameAll = parentCategory + "/" + childCategory;
        Category category = categoryService.findByNameAllGrandChild(nameAll);

        ItemAndCategory itemAndCategory = itemAndCategoryService.searchByCategory(nameAll);

        if (category == null && itemAndCategory == null) {
            categoryService.deleteCategoryByNameAll(nameAll);
        } else {
            redirectAttributes.addFlashAttribute("error", "cannot be deleted");
        }

        return "redirect:/findAllCategory";
    }
}
