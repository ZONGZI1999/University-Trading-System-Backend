package com.zekizheng.trading.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zekizheng.trading.dto.HttpBaseResponse;
import com.zekizheng.trading.dto.ResponseCode;
import com.zekizheng.trading.entity.ItemDetails;
import com.zekizheng.trading.mapper.ItemDetailsMapper;
import com.zekizheng.trading.service.ItemService;
import com.zekizheng.trading.status.ItemDetailsStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zongzi
 **/
@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/HomePage")
public class HomePageController {

    @Autowired
    private ItemDetailsMapper mapper;

    @GetMapping("/getNewSell")
    public HttpBaseResponse<List<ItemDetails>> newSell(){
        HttpBaseResponse<List<ItemDetails>> resp = new HttpBaseResponse<>();
        QueryWrapper<ItemDetails> wrapper = new QueryWrapper<>();
        wrapper.like("item_status", ItemDetailsStatus.ON_SELL);
        wrapper.orderByDesc("create_time");
        List<ItemDetails> res = mapper.selectList(wrapper);
        resp.setMessage(ResponseCode.SUCCESS);
        resp.setData(res);
        return resp;
    }
}
