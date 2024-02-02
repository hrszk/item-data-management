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

    /**
     * 中カテゴリ編集
     * 
     * @param nameAll 変更前のカテゴリ名（フル）
     * @param form    カテゴリ情報のフォーム
     * @return カテゴリ一覧
     */
    @PostMapping("/updateChildCategory")
    public String updateChildCategory(UpdateCategoryForm form) {

        // カテゴリ名(フル)の編集
        String parentCategory = StringUtils.substringBefore(form.getNameAll(), "/");
        String childCategoryNameAll = parentCategory + "/" + form.getName();

        // 変更したいカテゴリ情報をセット
        Category category = new Category();
        BeanUtils.copyProperties(form, category);
        category.setNameAll(childCategoryNameAll);
        // 中カテゴリの更新
        categoryService.updateCategory(category);

        // 中カテゴリに紐づく小カテゴリの一覧取得
        List<Category> categoryList = categoryService.findCategoryByNameAll(form.getNameAll());

        for (Category category2 : categoryList) {
            String grandChildNameAll = childCategoryNameAll + "/" + category2.getName();
            categoryService.updateNameAll(grandChildNameAll, category2.getId());
        }

        return "redirect:/showCategoryList";
    }

    @PostMapping("/updateParentCategory")
    public String updateParentCategory(String oldName, UpdateCategoryForm form) {

        // 変更したいカテゴリ情報をセット
        Category category = new Category();
        BeanUtils.copyProperties(form, category);
        // 大カテゴリの更新
        categoryService.updateCategory(category);

        // 大カテゴリに紐づく小カテゴリの一覧取得
        List<Category> categoryList = categoryService.findCategoryByNameAll(oldName);

        for (Category category2 : categoryList) {

            if (category2.getParent() == 1) {
                String ChildCategoryNameAll = form.getName() + "/" + category2.getName();
                categoryService.updateNameAll(ChildCategoryNameAll, category2.getId());

            } else {
                String grandChildNameAll = category2.getNameAll().replaceFirst(oldName, form.getName());
                categoryService.updateNameAll(grandChildNameAll, category2.getId());
            }
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
