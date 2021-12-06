package com.zekizheng.trading.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.GsonTypeHandler;
import com.zekizheng.trading.status.OrderStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zongzi
 **/
@Data
@TableName(autoResultMap = true)
public class OrderDetails implements Serializable {
    @TableId(type = IdType.ASSIGN_UUID)
    private String orderId;
    private String payNo;
    private String sellerId;
    private String buyerId;
    private Integer itemId;

    private String remark;
    @TableField(typeHandler = GsonTypeHandler.class)
    private DeliveryInfo deliveryInfo;
    private Date deliveryTime;
    private String deliveryCompany;
    private String trackingNo;

    private String sellerEvaluation;
    private String buyerEvaluation;

    private OrderStatus orderStatus;
    private Date createTime;

}
