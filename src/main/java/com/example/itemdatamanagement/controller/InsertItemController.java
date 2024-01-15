package com.example.itemdatamanagement.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.itemdatamanagement.domain.Image;
import com.example.itemdatamanagement.domain.Item;
import com.example.itemdatamanagement.form.InsertItemForm;
import com.example.itemdatamanagement.service.ImageService;
import com.example.itemdatamanagement.service.ItemService;

@Controller
@RequestMapping({ "", "/" })
public class InsertItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ImageService imageService;

    private String imageFolder = "src/main/resources/static/item_image/";
    private String ImgExtract = "jpg";

    @GetMapping("/toPageAddItem")
    public String toPageAddItem(Model model) {
        model.addAttribute("insertItemForm", new InsertItemForm());
        return "item/add";
    }

    @PostMapping("/addItem")
    public String addItem(InsertItemForm form, Model model) throws IOException {

        Item item = new Item();
        BeanUtils.copyProperties(form, item);
        item.setCategory(1410);
        item.setShipping(0);
        itemService.insertItem(item);

        Item item2 = itemService.findByNewItem();

        // 保存する画像ファイルのパス設定
        String saveFileName = item2.getId() + "." + ImgExtract;
        Path imgFilePath = Path.of(imageFolder, saveFileName);

        // 画像ファイルを保存
        Files.copy(form.getImage().getInputStream(), imgFilePath);

        Image image = new Image();
        image.setItemId(item2.getId());
        image.setImagePath(saveFileName);
        imageService.insertImage(image);

        return "redirect:/findAll";
    }
}
