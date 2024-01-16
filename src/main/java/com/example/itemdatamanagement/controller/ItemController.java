package com.example.itemdatamanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.itemdatamanagement.domain.Category;
import com.example.itemdatamanagement.domain.Image;
import com.example.itemdatamanagement.domain.Item;
import com.example.itemdatamanagement.domain.ItemAndCategory;
import com.example.itemdatamanagement.service.CategoryService;
import com.example.itemdatamanagement.service.ImageService;
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

    @GetMapping("/findAll")
    public String findAll(Model model) {
        List<ItemAndCategory> itemAndCategoryList = itemService.findAll();
        model.addAttribute("itemAndCategoryList", itemAndCategoryList);

        List<Category> parentCategoryList = categoryService.findAllParentCategory();
        model.addAttribute("parentCategoryList", parentCategoryList);

        List<Category> childCategoryList = categoryService.findAllChildCategory();
        model.addAttribute("childCategoryList", childCategoryList);

        List<Category> grandChildList = categoryService.findAllGrandChild();
        model.addAttribute("grandChildList", grandChildList);
        return "item/list";
    }

    @GetMapping("/findByName")
    public String findByName(String name, Model model) {
        List<ItemAndCategory> itemAndCategoryList = itemService.findByName(name);
        model.addAttribute("itemAndCategoryList", itemAndCategoryList);
        return "item/list";
    }

    @GetMapping("/showItemDetail")
    public String showItemDetail(Integer id, Model model) {
        ItemAndCategory itemAndCategory = itemService.findById(id);
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
        return "redirect:/findAll";
    }

    @PostMapping("/deleteItem")
    public String deleteItem(Integer id) {
        // Item item = itemService.findByIdItem(id);
        // Integer categoryId = item.getCategory();
        // categoryService.deleteCategory(categoryId);
        itemService.deleteItem(id);
        return "redirect:/findAll";
    }

}
