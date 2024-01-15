package com.example.itemdatamanagement.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.itemdatamanagement.domain.Image;
import com.example.itemdatamanagement.domain.Item;
import com.example.itemdatamanagement.form.UpdateItemForm;
import com.example.itemdatamanagement.service.ImageService;
import com.example.itemdatamanagement.service.ItemService;

@Controller
@RequestMapping({ "", "/" })
public class UpdateItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ImageService imageService;

    private String imageFolder = "src/main/resources/static/item_image/";
    private String ImgExtract = "jpg";

    @GetMapping("/toPageUpdateItem")
    public String toPageUpdateItem(Integer id, Model model) {
        Item item = itemService.findByIdItem(id);
        model.addAttribute("item", item);
        Image image = imageService.findByIdImage(id);
        model.addAttribute("image", image);
        return "item/edit";
    }

    @PostMapping("/updateItem")
    public String updateItem(UpdateItemForm form) throws IOException {

        Item item = new Item();
        BeanUtils.copyProperties(form, item);

        Item item2 = itemService.findByIdItem(item.getId());
        item.setCategory(item2.getCategory());
        itemService.updateItem(item);

        Image image = imageService.findByIdImage(item.getId());
        if (image == null) {
            // 保存する画像ファイルのパス設定
            String saveFileName = item.getId() + "." + ImgExtract;
            Path imgFilePath = Path.of(imageFolder, saveFileName);

            // 画像ファイルを保存
            Files.copy(form.getImage().getInputStream(), imgFilePath);

            Image image2 = new Image();
            image2.setItemId(item.getId());
            image2.setImagePath(saveFileName);
            imageService.insertImage(image2);

        } else {
            Path p = Paths.get(imageFolder + image.getImagePath());
            Files.delete(p);

            String saveFileName = item.getId() + "." + ImgExtract;
            Path imgFilePath = Path.of(imageFolder, saveFileName);

            Files.copy(form.getImage().getInputStream(), imgFilePath);
        }
        return "redirect:/findAll";
    }

}
