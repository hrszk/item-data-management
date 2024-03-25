package com.example.itemdatamanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.itemdatamanagement.service.ItemService;

@Controller
@RequestMapping({ "", "/" })
public class DeleteItemController {
    @Autowired
    private ItemService itemService;

    Logger logger = LoggerFactory.getLogger(DeleteItemController.class);


    /**
     * 商品の論理削除
     * 
     * @param id 商品ID
     * @return 商品一覧
     */
    @PostMapping("/deleteItem")
    public String deleteItem(Integer id) {

        itemService.deleteItem(id);
        logger.info("id {} の商品を削除しました", id);
        return "redirect:/showItemList";
    }
}
