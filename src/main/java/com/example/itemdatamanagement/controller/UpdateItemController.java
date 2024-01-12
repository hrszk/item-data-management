package com.example.itemdatamanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.itemdatamanagement.domain.Item;
import com.example.itemdatamanagement.service.ItemService;

@Controller
@RequestMapping({ "", "/" })
public class UpdateItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/toPageUpdateItem")
    public String toPageUpdateItem(Integer id, Model model) {
        Item item = itemService.findByIdItem(id);
        model.addAttribute("item", item);
        return "item/edit";
    }

    @PostMapping("/updateItem")
    public String updateItem(Item item) {
        Item item2 = itemService.findByIdItem(item.getId());
        item.setCategory(item2.getCategory());
        itemService.updateItem(item);
        return "redirect:/findAll";
    }

}
