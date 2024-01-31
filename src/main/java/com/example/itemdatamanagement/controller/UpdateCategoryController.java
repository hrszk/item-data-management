package com.example.itemdatamanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.StringUtils;

import com.example.itemdatamanagement.domain.Category;
import com.example.itemdatamanagement.service.CategoryService;

@Controller
@RequestMapping({ "", "/" })
public class UpdateCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/toPageEditParentCategory")
    public String toPageEditParentCategory(Model model) {

        List<Category> parentCategoryList = categoryService.findAllParentCategory();
        model.addAttribute("parentCategoryList", parentCategoryList);
        return "category/edit-parent";
    }

    /**
     * 中カテゴリの編集
     * 
     * @param name    カテゴリ名
     * @param nameAll カテゴリ名（フル）
     * @param id      カテゴリID
     * @return カテゴリ一覧
     */
    @PostMapping("/editChildCategory")
    public String editChildCategory(String name, String nameAll, Integer id) {

        String parentCategory = StringUtils.substringBefore(nameAll, "/");
        categoryService.updateChildCategoryAndGrandChild(name, parentCategory + "/" + name, id);
        return "redirect:/showCategoryList";
    }

    @PostMapping("/editGrandChild")
    public String editGrandChild(String name, String nameAll, Integer id) {

        return "redirect:/showCategoryList";
    }

}
