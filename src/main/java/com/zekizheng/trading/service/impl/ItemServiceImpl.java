package com.zekizheng.trading.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zekizheng.trading.entity.ItemDetails;
import com.zekizheng.trading.mapper.ItemDetailsMapper;
import com.zekizheng.trading.service.ItemService;
import com.zekizheng.trading.status.ItemDetailsStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zongzi
 **/
@Service
@Slf4j
@Transactional(isolation = Isolation.SERIALIZABLE)
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDetailsMapper mapper;

    @Override
    public int postNewItem(ItemDetails itemDetails) {
        log.info("post new item");
        itemDetails.setItemStatus(ItemDetailsStatus.ON_SELL);
        int row = mapper.insert(itemDetails);
        log.info("successful insert into db.");
        log.debug("db: " + itemDetails);
        return row;
    }

    @Override
    public List<ItemDetails> getAllItems(String studentId) {
        List<ItemDetails> itemDetails;
        if (studentId == null || studentId.equals("")) {
            itemDetails = mapper.selectList(null);
        } else {
            QueryWrapper<ItemDetails> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("seller_id", studentId);
            itemDetails = mapper.selectList(queryWrapper);
        }
        return itemDetails;
    }

    @Override
    public int updateItem(ItemDetails itemDetails) {
        log.info("start update item");
        int row = mapper.updateById(itemDetails);
        log.info("successfully update");
        log.debug("after update: " + itemDetails);
        return row;
    }

    @Override
    public int deleteItem(ItemDetails itemDetails) {
        log.info("start to delete item.");
        int row = mapper.deleteById(itemDetails);
        log.info("successfully delete");
        return row;
    }

    @Override
    public ItemDetails queryOneItem(Integer itemId) {
        if (itemId == null) {
            log.error("item id is null");
            return new ItemDetails();
        }
        log.info("query db");
        ItemDetails res = mapper.selectById(itemId);
        log.info("query result is: " + res);
        return res;
    }


}
