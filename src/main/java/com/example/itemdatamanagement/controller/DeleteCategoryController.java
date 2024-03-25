package com.example.itemdatamanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.itemdatamanagement.service.CategoryService;

@Controller
@RequestMapping({ "", "/" })
public class DeleteCategoryController {

    @Autowired
    private CategoryService categoryService;

    Logger logger=LoggerFactory.getLogger(DeleteCategoryController.class);

    /**
     * カテゴリの削除
     * 
     * @param nameAll カテゴリ名（フル）
     * @param model   スコープの準備
     * @return カテゴリ一覧表示
     */
    @PostMapping("/deleteCategory")
    public String deleteCategory(String nameAll) {
        categoryService.deleteCategoryByNameAll(nameAll);
        logger.info("カテゴリ名 {} を削除しました",nameAll);
        return "redirect:/showCategoryList";
    }

    @PostMapping("/deleteParentCategory")
    public String deleteParentCategory(String name) {
        categoryService.deleteParentCategory(name);
        logger.info("親カテゴリ {} を削除しました",name);
        return "redirect:/showCategoryList";
    }
}
