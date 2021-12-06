package com.zekizheng.trading.service;

import com.zekizheng.trading.entity.OrderDetails;

import java.util.List;

/**
 * @author zongzi
 **/
public interface OrderService {
    int createOrder(OrderDetails orderDetails);
    int payOrder(OrderDetails orderDetails);
    OrderDetails deliveryOrder(OrderDetails orderDetails);

    List<OrderDetails> queryAllOrder(String studentId);
    OrderDetails queryDetails(String orderId);
}
