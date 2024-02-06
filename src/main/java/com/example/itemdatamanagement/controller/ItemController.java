package com.example.itemdatamanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Page;

import com.example.itemdatamanagement.domain.Category;
import com.example.itemdatamanagement.domain.Image;
import com.example.itemdatamanagement.domain.ItemAndCategory;
import com.example.itemdatamanagement.form.SearchItemForm;
import com.example.itemdatamanagement.service.CategoryService;
import com.example.itemdatamanagement.service.ImageService;
import com.example.itemdatamanagement.service.ItemAndCategoryService;
import com.example.itemdatamanagement.service.ItemService;

@Controller
@RequestMapping({ "", "/" })
public class ItemController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ItemAndCategoryService itemAndCategoryService;

    // 1ページに表示する商品は30個
    private static final int VIEW_SIZE = 30;

    @RequestMapping("/showItemList")
    public String showItemList(SearchItemForm form, Model model, Integer page) {
        model.addAttribute("form", form);

        List<ItemAndCategory> itemAndCategoryList = null;

        // ページング機能追加
        if (page == null) {
            // ページ数の指定が無い場合は1ページ目を表示させる
            page = 1;
        }
        model.addAttribute("page", page);

        String nameAll;

        if (form.getParentCategory() == null) {
            if (form.getParentCategory() != null && form.getChildCategory() != null
                    && form.getGrandChild() != null) {
                nameAll = form.getParentCategory() + "/" + form.getChildCategory() + "/" + form.getGrandChild();
            } else if (form.getParentCategory() != null && form.getChildCategory() != null
                    && form.getGrandChild() == null) {
                nameAll = form.getParentCategory() + "/" + form.getChildCategory();
            } else if (form.getParentCategory() != null && form.getChildCategory() == null
                    && form.getGrandChild() == null) {
                nameAll = form.getParentCategory();
            } else {
                nameAll = null;
            }
        } else {
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
                nameAll = "%";
            }
        }

        if (form.getName() == null && nameAll == null && form.getBrand() == null) {
            // 検索ボックスが空なら全件検索
            itemAndCategoryList = itemAndCategoryService.findAll();
            model.addAttribute("itemAndCategoryList", itemAndCategoryList);
        } else {
            // 検索ボックスに入力があれば条件を指定して検索
            itemAndCategoryList = itemAndCategoryService.searchItem(form.getName(), nameAll, form.getBrand());

            // ページングの数字からも検索できるように検索条件をスコープに格納しておく
            model.addAttribute("form", form);

            // 該当する検索結果がなければ全件返す
            if (itemAndCategoryList == null) {
                itemAndCategoryList = itemAndCategoryService.findAll();
                model.addAttribute("notFindAll", "Not Find");
            }
        }

        // 表示させたいページ数、ページサイズ、商品リストを渡し１ページに表示させる商品リストを絞り込み
        Page<ItemAndCategory> itemPage = itemAndCategoryService.showListPaging(page, VIEW_SIZE, itemAndCategoryList);
        model.addAttribute("itemPage", itemPage);

        // ページングのリンクに使うページ数をスコープに格納 (例)28件あり1ページにつき10件表示させる場合→1,2,3がpageNumbersに入る
        int totalPages = itemPage.getTotalPages();
        model.addAttribute("totalPages", totalPages);

        List<Category> parentCategoryList = categoryService.findAllParentCategory();
        model.addAttribute("parentCategoryList", parentCategoryList);

        List<Category> childCategoryList = categoryService.findAllChildCategory();
        model.addAttribute("childCategoryList", childCategoryList);

        List<Category> grandChildList = categoryService.findAllGrandChild();
        model.addAttribute("grandChildList", grandChildList);
        return "item/list";
    }

    @GetMapping("/showItemDetail")
    public String showItemDetail(Integer id, Model model) {
        ItemAndCategory itemAndCategory = itemAndCategoryService.findById(id);
        model.addAttribute("itemAndCategory", itemAndCategory);

        Image image = imageService.findByIdImage(id);

        if (image == null) {
            model.addAttribute("text", "no image");
        } else {
            model.addAttribute("image", image);
        }
        return "item/detail";
    }

    @GetMapping("/toPageItemList")
    public String toPageItemList() {
        return "redirect:/showItemList";
    }

}
