package com.example.itemdatamanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.itemdatamanagement.domain.Category;
import com.example.itemdatamanagement.domain.ItemAndCategory;
import com.example.itemdatamanagement.service.CategoryService;
import com.example.itemdatamanagement.service.ItemAndCategoryService;

@Controller
@RequestMapping({ "", "/" })
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ItemAndCategoryService itemAndCategoryService;

    /**
     * カテゴリ一覧の表示
     * 
     * @param model リクエストスコープを使うための準備
     * @return カテゴリ一覧
     */
    @GetMapping("/showCategoryList")
    public String showCategoryList(Model model) {
        List<Category> parentCategoryList = categoryService.findAllParentCategory();
        model.addAttribute("parentCategoryList", parentCategoryList);

        List<Category> childCategoryList = categoryService.findAllChildCategory();
        model.addAttribute("childCategoryList", childCategoryList);

        List<Category> grandChildList = categoryService.findAllGrandChild();
        model.addAttribute("grandChildList", grandChildList);

        return "category/list";
    }

    @GetMapping("/showEditCategory")
    public String showEditCategory(Integer id, Model model) {
        Category category = categoryService.findByIdCategory(id);
        model.addAttribute("category", category);
        return "category/edit";
    }

    @GetMapping("/showGrandChild")
    public String showGrandChild(Integer id, String nameAll, Model model) {
        Category category = categoryService.findByIdCategory(id);
        model.addAttribute("category", category);

        String[] categories = category.getNameAll().split("/");
        model.addAttribute("parentCategory", categories[0]);
        model.addAttribute("childCategory", categories[1]);

        ItemAndCategory itemAndCategory = itemAndCategoryService.searchByCategory(nameAll);
        if (itemAndCategory != null) {
            model.addAttribute("error", "cannot be deleted");
        }
        return "category/edit-grandchild";
    }

}
