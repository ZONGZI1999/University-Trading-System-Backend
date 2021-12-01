package com.zekizheng.trading.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zekizheng.trading.status.OrderStatus;
import lombok.Data;

import java.util.Date;

/**
 * @author zongzi
 **/
@Data
@TableName
public class OrderDetails {
    @TableId(type = IdType.ASSIGN_UUID)
    private String orderId;
    private String payNo;
    private String buyerId;
    private Integer itemId;

    private String remark;
    private Integer deliveryInfoId;
    private Date deliveryTime;
    private String deliveryCompany;
    private String trackingNo;

    private String sellerEvaluation;
    private String buyerEvaluation;

    private OrderStatus orderStatus;
    private Date createTime;

}
