package com.zekizheng.trading.service;

import com.zekizheng.trading.entity.OrderDetails;

import java.util.List;

/**
 * @author zongzi
 **/
public interface OrderService {
    int createOrder(OrderDetails orderDetails);
    int payOrder(OrderDetails orderDetails);
    int updateOrder(OrderDetails orderDetails);

    List<OrderDetails> queryAllOrderAsSeller(String studentId);
    List<OrderDetails> queryAllOrderAsBuyer(String studentId);
    List<OrderDetails> queryAllOrder(String studentId);
    OrderDetails queryDetails(String orderId);

    OrderDetails queryDetailsByItemId(Integer itemId);

}
