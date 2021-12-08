package com.zekizheng.trading.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zekizheng.trading.dto.HttpBaseResponse;
import com.zekizheng.trading.dto.ResponseCode;
import com.zekizheng.trading.entity.DeliveryInfo;
import com.zekizheng.trading.mapper.DeliveryInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zongzi
 **/

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/delivery")
@SaCheckLogin
public class DeliveryInfoController {

    @Autowired
    private DeliveryInfoMapper mapper;

    @GetMapping("/getDeliveryInfoList")
    public HttpBaseResponse<List<DeliveryInfo>> getDeliveryInfoList(@RequestParam String studentId) {
        HttpBaseResponse<List<DeliveryInfo>> resp = new HttpBaseResponse<>();
        List<DeliveryInfo> deliveryInfoList = null;
        String userId = StpUtil.getLoginIdAsString();
        if (userId.equals(studentId)){
            resp.setMessage(ResponseCode.UNKNOWN_ERROR);
            resp.setDescription("You are not allowed to query this student id delivery address.");
            return resp;
        }
        try {
            deliveryInfoList
                    = mapper.selectList(new QueryWrapper<DeliveryInfo>().eq("student_id", studentId));
        }catch (Exception e) {
            e.printStackTrace();
            log.error("query delivery info fail");
            resp.setMessage(ResponseCode.DATABASE_ERROR);
            return resp;
        }
        if(deliveryInfoList == null) {
            resp.setMessage(ResponseCode.DATABASE_ERROR);
            log.error("delivery info list is null");
            return resp;
        }
        resp.setMessage(ResponseCode.SUCCESS);
        resp.setData(deliveryInfoList);
        return resp;
    }

    @PostMapping("/updateDeliveryInfo")
    public HttpBaseResponse<DeliveryInfo> updateDeliveryInfo(@RequestBody DeliveryInfo deliveryInfo) {
        HttpBaseResponse<DeliveryInfo> resp = new HttpBaseResponse<>();
        String studentId = StpUtil.getLoginIdAsString();
        if(deliveryInfo.getDeliveryInfoId() == null) {
            resp.setMessage(ResponseCode.PARAM_ERROR);
            resp.setDescription("Delivery info id is null");
            return resp;
        }
        String name = deliveryInfo.getName();
        String phoneNo = deliveryInfo.getPhoneNo();
        String address = deliveryInfo.getAddress();

        deliveryInfo = mapper.selectById(deliveryInfo.getDeliveryInfoId());

        if (deliveryInfo == null) {
            resp.setMessage(ResponseCode.PARAM_ERROR);
            resp.setDescription("Delivery info id is error");
            return resp;
        }

        if (!deliveryInfo.getStudentId().equals(studentId)) {
            resp.setMessage(ResponseCode.UNKNOWN_ERROR);
            resp.setDescription("You cannot to modify it");
            return resp;
        }

        deliveryInfo.setName(name);
        deliveryInfo.setPhoneNo(phoneNo);
        deliveryInfo.setAddress(address);

        int row = mapper.updateById(deliveryInfo);

        if (row != 1){
            resp.setMessage(ResponseCode.DATABASE_ERROR);
            return resp;
        }

        resp.setMessage(ResponseCode.SUCCESS);
        resp.setData(deliveryInfo);
        return resp;

    }

    @PostMapping("/insertDeliveryInfo")
    public HttpBaseResponse<DeliveryInfo> insertDeliveryInfo(@RequestBody DeliveryInfo deliveryInfo) {
        HttpBaseResponse<DeliveryInfo> resp = new HttpBaseResponse<>();

        String studentId = StpUtil.getLoginIdAsString();
        deliveryInfo.setStudentId(studentId);
        int row = mapper.insert(deliveryInfo);
        if (row != 1) {
            log.error("insert new delivery info fail");
            resp.setMessage(ResponseCode.DATABASE_ERROR);
            return resp;
        }
        resp.setMessage(ResponseCode.SUCCESS);
        resp.setData(deliveryInfo);
        return resp;
    }

}
