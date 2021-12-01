package com.zekizheng.trading.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zongzi
 **/
@Data
@TableName
public class DeliveryInfo {
    @TableId(type = IdType.AUTO)
    private Integer deliveryInfoId;
    private String studentId;
    private String name;
    private String phoneNo;
    private String address;
}
