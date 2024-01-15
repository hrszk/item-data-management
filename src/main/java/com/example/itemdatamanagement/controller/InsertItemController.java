package com.example.itemdatamanagement.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.itemdatamanagement.domain.Item;
import com.example.itemdatamanagement.form.InsertItemForm;
import com.example.itemdatamanagement.service.ItemService;

@Controller
@RequestMapping({ "", "/" })
public class InsertItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/toPageAddItem")
    public String toPageAddItem() {
        return "item/add";
    }

    @PostMapping("/addItem")
    public String addItem(InsertItemForm form, Model model) {

        Item item = new Item();
        BeanUtils.copyProperties(form, item);
        item.setCategory(1410);
        item.setShipping(0);

        itemService.insertItem(item);
        return "redirect:/findAll";
    }
}
