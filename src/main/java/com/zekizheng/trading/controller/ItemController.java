package com.zekizheng.trading.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.zekizheng.trading.dto.HttpBaseResponse;
import com.zekizheng.trading.dto.ResponseCode;
import com.zekizheng.trading.entity.ItemDetails;
import com.zekizheng.trading.entity.OrderDetails;
import com.zekizheng.trading.entity.UserType;
import com.zekizheng.trading.mapper.ItemDetailsMapper;
import com.zekizheng.trading.mapper.StudentMapper;
import com.zekizheng.trading.service.ItemService;
import com.zekizheng.trading.status.ItemDetailsStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zongzi
 **/

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/item")
@SaCheckLogin
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ItemDetailsMapper itemMapper;

    @GetMapping("/queryOneItem")
    public HttpBaseResponse<ItemDetails> queryOneItem(@RequestParam Integer itemId){
        HttpBaseResponse<ItemDetails> resp = new HttpBaseResponse<>();
        ItemDetails itemDetails = itemService.queryOneItem(itemId);
        if (itemDetails == null) {
            resp.setMessage(ResponseCode.PARAM_ERROR);
            resp.setDescription("Item Id not correct!");
            return resp;
        }
        itemDetails.setSellerId(studentMapper.selectById(itemDetails.getSellerId()).getStudentName());
        resp.setData(itemDetails);
        resp.setMessage(ResponseCode.SUCCESS);
        return resp;
    }

    @GetMapping("/queryItems")
    public HttpBaseResponse<List<ItemDetails>> queryItems() {
        //TODO: 判断用户属性
        HttpBaseResponse<List<ItemDetails>> resp = new HttpBaseResponse<>();
        UserType userType = UserType.STUDENT;
        List<ItemDetails> itemDetails;
        switch (userType){
            case ADMIN:
                itemDetails = itemService.getAllItems("");
                resp.setData(itemDetails);
                resp.setMessage(ResponseCode.SUCCESS);
                break;
            case STUDENT:
                String studentId = StpUtil.getLoginIdAsString();
                itemDetails = itemService.getAllItems(studentId);
                resp.setData(itemDetails);
                resp.setMessage(ResponseCode.SUCCESS);
                break;
            default:
                resp.setData(new ArrayList<>());
                resp.setMessage(ResponseCode.USER_TYPE_ERROR);
                break;
        }
        return resp;
    }

    @PostMapping("/postNewItem")
    public HttpBaseResponse<ItemDetails> postNew(@RequestBody ItemDetails newOne) {
        log.info("start to post new item");
        HttpBaseResponse<ItemDetails> resp = new HttpBaseResponse<>();
        String studentId = StpUtil.getLoginIdAsString();
        newOne.setSellerId(studentId);
        newOne.setItemStatus(ItemDetailsStatus.ON_SELL);
        int row = itemService.postNewItem(newOne);

        if (row == 0) {
            resp.setMessage(ResponseCode.DATABASE_ERROR);
            resp.setData(new ItemDetails());
            return resp;
        }
        resp.setMessage(ResponseCode.SUCCESS);
        resp.setData(newOne);
        log.info("insert new item resp: " + resp);
        return resp;
    }

    @PostMapping("/updateItem")
    public HttpBaseResponse<ItemDetails> update(@RequestBody ItemDetails itemDetails){
        HttpBaseResponse<ItemDetails> resp = new HttpBaseResponse<>();
        Integer itemId = itemDetails.getItemId();
        if(itemId == null) {
            resp.setMessage(ResponseCode.PARAM_ERROR);
            resp.setDescription("Item Id cannot be empty");
            return resp;
        }

        ItemDetails beforeUpdate = itemService.queryOneItem(itemId);
        String studentId = StpUtil.getLoginIdAsString();
        if (!beforeUpdate.getSellerId().equals(studentId)){
            resp.setMessage(ResponseCode.UNKNOWN_ERROR);
            resp.setDescription("You cannot modify this item details");
            return resp;
        }

        if (beforeUpdate.getItemStatus() != ItemDetailsStatus.ON_SELL){
            resp.setMessage(ResponseCode.INTERNAL_ERROR);
            resp.setDescription("This item is SOLD. you cannot modify it!");
            return resp;
        }
        itemDetails.setSellerId(studentId);
        int row = itemService.updateItem(itemDetails);

        if (row == 0) {
            resp.setMessage(ResponseCode.DATABASE_ERROR);
            resp.setData(new ItemDetails());
            return resp;
        }
        resp.setMessage(ResponseCode.SUCCESS);
        resp.setData(itemDetails);
        return resp;
    }
}
