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
     * 中カテゴリ編集
     * 
     * @param nameAll 変更前のカテゴリ名（フル）
     * @param form    カテゴリ情報のフォーム
     * @return カテゴリ一覧
     */
    @PostMapping("/updateChildCategory")
    public String updateChildCategory(String nameAll, UpdateCategoryForm form) {

        // カテゴリ名(フル)の編集
        String parentCategory = StringUtils.substringBefore(nameAll, "/");
        String childCategoryNameAll = parentCategory + "/" + form.getName();

        // 変更したいカテゴリ情報をセット
        Category category = new Category();
        BeanUtils.copyProperties(form, category);
        category.setNameAll(childCategoryNameAll);
        // 中カテゴリの更新
        categoryService.updateCategory(category);

        // 中カテゴリに紐づく小カテゴリの一覧取得
        List<Category> categoryList = categoryService.findCategoryByNameAll(nameAll);

        for (Category category2 : categoryList) {
            String grandChildNameAll = childCategoryNameAll + "/" + category2.getName();
            categoryService.updateNameAll(grandChildNameAll, category2.getId());
        }

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

        categoryService.updateCategory(category);

        return "redirect:/showCategoryList";
    }

}
