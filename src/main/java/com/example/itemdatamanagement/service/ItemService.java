package com.example.itemdatamanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.itemdatamanagement.domain.Category;
import com.example.itemdatamanagement.domain.Item;
import com.example.itemdatamanagement.domain.ItemAndCategory;
import com.example.itemdatamanagement.repository.ItemRepository;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<ItemAndCategory> findAll() {
        return itemRepository.findAll();
    }

    // public List<Item> findAllItem() {
    // return itemRepository.findAllItem();
    // }

    // public Category findByIdCategory(Integer id) {
    // return itemRepository.findByIdCategory(id);
    // }

    public ItemAndCategory findById(Integer id) {
        return itemRepository.findById(id);
    }

}
