package com.example.itemdatamanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.itemdatamanagement.domain.Category;
import com.example.itemdatamanagement.domain.ItemAndCategory;
import com.example.itemdatamanagement.form.SearchItemForm;
import com.example.itemdatamanagement.service.CategoryService;
import com.example.itemdatamanagement.service.ItemAndCategoryService;

@Controller
@RequestMapping({ "", "/" })
public class searchItemController {

    @Autowired
    private ItemAndCategoryService itemAndCategoryService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/searchItem")
    public String searchItem(SearchItemForm form, Model model) {

        String nameAll;
        if (!form.getParentCategory().isEmpty() && !form.getChildCategory().isEmpty()
                && !form.getGrandChild().isEmpty()) {
            nameAll = form.getParentCategory() + "/" + form.getChildCategory() + "/" + form.getGrandChild();
        } else if (!form.getParentCategory().isEmpty() && !form.getChildCategory().isEmpty()
                && form.getGrandChild().isEmpty()) {
            nameAll = form.getParentCategory() + "/" + form.getChildCategory();
        } else if (!form.getParentCategory().isEmpty() && form.getChildCategory().isEmpty()
                && form.getGrandChild().isEmpty()) {
            nameAll = form.getParentCategory();
        } else {
            nameAll = null;
        }

        // if (form.getName() != null && nameAll != null && form.getBrand() != null) {
        List<ItemAndCategory> itemAndCategoryList = itemAndCategoryService.searchItem(form.getName(), nameAll,
                form.getBrand());
        model.addAttribute("itemAndCategoryList", itemAndCategoryList);
        // }

        List<Category> parentCategoryList = categoryService.findAllParentCategory();
        model.addAttribute("parentCategoryList", parentCategoryList);

        List<Category> childCategoryList = categoryService.findAllChildCategory();
        model.addAttribute("childCategoryList", childCategoryList);

        List<Category> grandChildList = categoryService.findAllGrandChild();
        model.addAttribute("grandChildList", grandChildList);

        return "item/list";
    }
}
