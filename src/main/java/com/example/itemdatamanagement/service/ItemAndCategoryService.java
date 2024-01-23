package com.example.itemdatamanagement.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.itemdatamanagement.domain.ItemAndCategory;
import com.example.itemdatamanagement.repository.ItemAndCategoryRepository;

@Service
public class ItemAndCategoryService {

    @Autowired
    private ItemAndCategoryRepository itemAndCategoryRepository;

    public List<ItemAndCategory> searchItem(String name, String nameAll, String brand) {
        return itemAndCategoryRepository.searchItem(name, nameAll, brand);
    }

    public Page<ItemAndCategory> showListPaging(int page, int size, List<ItemAndCategory> itemAndCategoryList) {

        // 表示させたいページ数を-1しなければうまく動かない
        page--;
        // どの商品から表示させるかと言うカウント値
        int startItemCount = page * size;
        // 絞り込んだ後の商品リストが入る変数
        List<ItemAndCategory> list;

        if (itemAndCategoryList.size() < startItemCount) {
            // (ありえないが)もし表示させたい商品カウントがサイズよりも大きい場合は空のリストを返す
            list = Collections.emptyList();
        } else {
            // 該当ページに表示させる商品一覧を作成
            int toIndex = Math.min(startItemCount + size, itemAndCategoryList.size());
            list = itemAndCategoryList.subList(startItemCount, toIndex);
        }

        // 上記で作成した該当ページに表示させる従業員一覧をページングできる形に変換して返す
        Page<ItemAndCategory> itemPage = new PageImpl<ItemAndCategory>(list, PageRequest.of(page, size),
                itemAndCategoryList.size());
        return itemPage;
    }
}
