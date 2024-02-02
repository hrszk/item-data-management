package com.example.itemdatamanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

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

    /**
     * 小カテゴリの編集画面へ遷移
     * 
     * @param id    カテゴリID
     * @param model スコープの準備
     * @return 小カテゴリの編集画面
     */
    @GetMapping("/showGrandChild")
    public String showGrandChild(Integer id, Model model) {
        Category category = categoryService.findByIdCategory(id);
        model.addAttribute("category", category);

        String[] categories = category.getNameAll().split("/");
        model.addAttribute("parentCategory", categories[0]);
        model.addAttribute("childCategory", categories[1]);

        ItemAndCategory itemAndCategory = itemAndCategoryService.searchByCategory(category.getNameAll());
        if (itemAndCategory != null) {
            model.addAttribute("error", "cannot be deleted");
        }
        return "category/edit-grandchild";
    }

    /**
     * 中カテゴリの編集画面へ遷移
     * 
     * @param id    カテゴリID
     * @param model スコープの準備
     * @return 中カテゴリの編集画面
     */
    @GetMapping("/showCategory")
    public String showChildCategory(Integer id, Model model) {

        Category category = categoryService.findByIdCategory(id);
        model.addAttribute("category", category);

        if (category.getParent() == 1) {
            // カテゴリに紐づく商品登録がないか検索
            ItemAndCategory itemAndCategory = itemAndCategoryService.searchByCategory(category.getNameAll());
            // カテゴリに紐づく小カテゴリがないか検索
            Category category2 = categoryService.findCategoryByNameAllAndParent(category.getNameAll(), 2);

            // 商品と子カテゴリに紐づいている場合は削除ボタンにエラーメッセージを表示
            if (itemAndCategory != null || category2 != null) {
                model.addAttribute("error", "cannot be deleted");
            }
            return "category/edit-childcategory";

        } else {
            ItemAndCategory itemAndCategory = itemAndCategoryService.searchByCategory(category.getName());
            Category category2 = categoryService.findCategoryByNameAllAndParent(category.getName(), 1);

            if (itemAndCategory != null || category2 != null) {
                model.addAttribute("error", "cannot be deleted");
            }
            return "category/edit-parentcategory";

        }
    }

}
