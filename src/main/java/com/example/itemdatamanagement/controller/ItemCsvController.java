package com.example.itemdatamanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.itemdatamanagement.domain.ItemAndCategory;
import com.example.itemdatamanagement.domain.ItemCsv;
import com.example.itemdatamanagement.service.ItemAndCategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

@Controller
@RequestMapping("/csv")
public class ItemCsvController {
    @Autowired
    private ItemAndCategoryService itemAndCategoryService;

    @GetMapping(value = "/data.csv", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
            + "; charset=Shift_JIS; Content-Disposition: attachment")
    @ResponseBody
    public Object getCsv() throws JsonProcessingException {

        List<ItemCsv> itemCsvList = new ArrayList<>();
        List<ItemAndCategory> itemAndCategoryList = itemAndCategoryService.findAll();
        for (ItemAndCategory itemAndCategory : itemAndCategoryList) {
            ItemCsv itemCsv = new ItemCsv();
            itemCsv.setId(itemAndCategory.getId());
            itemCsv.setName(itemAndCategory.getName());
            itemCsv.setPrice(itemAndCategory.getPrice());
            itemCsv.setCategory(itemAndCategory.getNameAll());
            itemCsv.setBrand(itemAndCategory.getBrand());
            itemCsv.setCondition(itemAndCategory.getCondition());
            itemCsv.setUpdateTime(itemAndCategory.getUpdateTime());
            itemCsv.setDelFlg(itemAndCategory.getDelFlg());
            itemCsvList.add(itemCsv);
        }

        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(ItemCsv.class).withHeader();

        return mapper.writer(schema).writeValueAsString(itemCsvList);
    }

}
