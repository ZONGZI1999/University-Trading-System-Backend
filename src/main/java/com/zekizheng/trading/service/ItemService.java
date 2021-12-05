package com.zekizheng.trading.service;

import com.zekizheng.trading.entity.ItemDetails;

import java.util.List;

/**
 * @author zongzi
 **/
public interface ItemService {
    int postNewItem(ItemDetails itemDetails);
    List<ItemDetails> getAllItems(String studentId);
    int updateItem(ItemDetails itemDetails);
    int deleteItem(ItemDetails itemDetails);
    ItemDetails queryOneItem(Integer itemId);
}
