package com.example.itemdatamanagement.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.StringUtils;

import com.example.itemdatamanagement.domain.Category;
import com.example.itemdatamanagement.form.UpdateCategoryForm;
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

    /**
     * 小カテゴリの編集
     * 
     * @param name
     * @param nameAll
     * @param id
     * @return
     */
    @PostMapping("/updateGrandChild")
    public String updateGrandChild(UpdateCategoryForm form) {

        String[] categories = form.getNameAll().split("/");
        String nameAll = categories[0] + "/" + categories[1] + "/" + form.getName();

        Category category = new Category();
        BeanUtils.copyProperties(form, category);
        category.setNameAll(nameAll);
        category.setParent(2);

        categoryService.updateCategory(category);

        return "redirect:/showCategoryList";
    }

}
