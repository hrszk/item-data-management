package com.example.itemdatamanagement.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.itemdatamanagement.domain.Category;
import com.example.itemdatamanagement.domain.Image;
import com.example.itemdatamanagement.domain.Item;
import com.example.itemdatamanagement.form.InsertItemForm;
import com.example.itemdatamanagement.service.CategoryService;
import com.example.itemdatamanagement.service.ImageService;
import com.example.itemdatamanagement.service.ItemService;

@Controller
@RequestMapping({ "", "/" })
public class InsertItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private CategoryService categoryService;

    private String imageFolder = "src/main/resources/static/item_image/";
    private String ImgExtract = "jpg";

    @GetMapping("/toPageAddItem")
    public String toPageAddItem(Model model) {
        model.addAttribute("insertItemForm", new InsertItemForm());

        List<Category> parentCategoryList = categoryService.findAllParentCategory();
        model.addAttribute("parentCategoryList", parentCategoryList);

        List<Category> childCategoryList = categoryService.findAllChildCategory();
        model.addAttribute("childCategoryList", childCategoryList);

        List<Category> grandChildList = categoryService.findAllGrandChild();
        model.addAttribute("grandChildList", grandChildList);
        return "item/add";
    }

    @PostMapping("/addItem")
    public String addItem(InsertItemForm form, Model model) throws IOException {

        Item item = new Item();
        BeanUtils.copyProperties(form, item);
        item.setShipping(0);
        item.setDelFlg(0);

        String nameAll;
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
            nameAll = "";
        }

        if ("".equals(nameAll)) {
            item.setCategory(1410);
        } else {
            Category category = categoryService.findByNameCategory(nameAll);
            item.setCategory(category.getId());
        }

        itemService.insertItem(item);

        if (form.getImage().getSize() != 0) {

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

        }
        return "redirect:/showItemList";
    }
}
