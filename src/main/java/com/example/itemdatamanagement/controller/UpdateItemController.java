package com.example.itemdatamanagement.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.itemdatamanagement.domain.Category;
import com.example.itemdatamanagement.domain.Image;
import com.example.itemdatamanagement.domain.Item;
import com.example.itemdatamanagement.form.UpdateItemForm;
import com.example.itemdatamanagement.service.CategoryService;
import com.example.itemdatamanagement.service.ImageService;
import com.example.itemdatamanagement.service.ItemService;

@Controller
@RequestMapping({ "", "/" })
public class UpdateItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private CategoryService categoryService;

    private String imageFolder = "src/main/resources/static/item_image/";
    private String ImgExtract = "jpg";

    @GetMapping("/showEditItem")
    public String showEditItem(Integer id, Model model) {
        Item item = itemService.findByIdItem(id);
        model.addAttribute("item", item);

        Image image = imageService.findByIdImage(id);
        model.addAttribute("image", image);

        if (item.getCategory() != 1410) {
            Category category = categoryService.findByIdCategory(item.getCategory());
            String[] categories = category.getNameAll().split("/");
            model.addAttribute("categories", categories);
        }

        List<Category> parentCategoryList = categoryService.findAllParentCategory();
        model.addAttribute("parentCategoryList", parentCategoryList);

        List<Category> childCategoryList = categoryService.findAllChildCategory();
        model.addAttribute("childCategoryList", childCategoryList);

        List<Category> grandChildList = categoryService.findAllGrandChild();
        model.addAttribute("grandChildList", grandChildList);
        return "item/edit";
    }

    /**
     * 商品情報の更新
     * 
     * @param form 商品情報
     * @return 商品詳細画面
     * @throws IOException
     */
    @PostMapping("/updateItem")
    public String updateItem(@RequestParam("id") Integer id, UpdateItemForm form, RedirectAttributes attributes)
            throws IOException {
        attributes.addAttribute("id", form.getId());

        Item item = new Item();
        BeanUtils.copyProperties(form, item);

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
            Item item2 = itemService.findByIdItem(item.getId());
            item.setCategory(item2.getCategory());
        } else {
            Category category = categoryService.findByNameCategory(nameAll);
            item.setCategory(category.getId());
        }

        itemService.updateItem(item);

        if (form.getImage().getSize() != 0) {

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
        }
        return "redirect:/showItemDetail";
    }

}
