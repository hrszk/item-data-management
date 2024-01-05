package com.example.itemdatamanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.itemdatamanagement.domain.Category;
import com.example.itemdatamanagement.domain.Item;
import com.example.itemdatamanagement.domain.ItemAndCategory;
import com.example.itemdatamanagement.service.ItemService;

@Controller
@RequestMapping({ "", "/" })
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/findAll")
    public String findAll(Model model) {
        List<ItemAndCategory> itemAndCategoryList = itemService.findAll();
        model.addAttribute("itemAndCategoryList", itemAndCategoryList);
        return "item/list";
    }

    @GetMapping("/findByName")
    public String findByName(String name, Model model) {
        List<ItemAndCategory> itemAndCategoryList = itemService.findByName(name);
        model.addAttribute("itemAndCategoryList", itemAndCategoryList);
        return "item/list";
    }
}
