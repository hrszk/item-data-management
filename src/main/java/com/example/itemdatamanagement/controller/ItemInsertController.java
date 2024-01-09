package com.example.itemdatamanagement.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.itemdatamanagement.domain.Original;
import com.example.itemdatamanagement.form.InsertItemForm;
import com.example.itemdatamanagement.service.OriginalService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Controller
@RequestMapping({ "", "/" })
public class ItemInsertController {

    @Autowired
    private OriginalService originalService;

    @GetMapping("/toPageAddItem")
    public String toPageAddItem() {
        return "item/add";
    }

    @PostMapping("/addItem")
    public String addItem(InsertItemForm form, Model model) {
        String categoryName = form.getParentCategory() + "/" + form.getChildCategory() + "/" + form.getGrandChild();
        Original original = new Original();
        BeanUtils.copyProperties(form, original);
        original.setCategoryName(categoryName);

        return "item/list";
    }
}
