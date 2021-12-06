package com.zekizheng.trading.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zekizheng.trading.entity.ItemDetails;
import com.zekizheng.trading.entity.OrderDetails;
import com.zekizheng.trading.entity.UserBalance;
import com.zekizheng.trading.mapper.ItemDetailsMapper;
import com.zekizheng.trading.mapper.OrderDetailsMapper;
import com.zekizheng.trading.service.BalanceService;
import com.zekizheng.trading.service.ItemService;
import com.zekizheng.trading.service.OrderService;
import com.zekizheng.trading.status.ItemDetailsStatus;
import com.zekizheng.trading.status.OrderStatus;
import com.zekizheng.trading.utils.SnowflakeIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author zongzi
 **/
@Slf4j
@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailsMapper orderMapper;

    @Autowired
    private BalanceService balanceService;

    @Autowired
    private ItemDetailsMapper itemDetailsMapper;


    @Override
    public int createOrder(OrderDetails orderDetails) {
        int row = 0;
        orderDetails.setOrderStatus(OrderStatus.CREATED);
        try{
            row = orderMapper.insert(orderDetails);
        }catch (Exception e) {
            e.printStackTrace();
            log.error("insert order details fail");
            return 0;
        }

        return row;
    }

    @Override
    public int payOrder(OrderDetails orderDetails) {
        int row = 0;
        try {

            UserBalance userBalance = balanceService.getUserBalance(orderDetails.getBuyerId());
            ItemDetails itemDetails = itemDetailsMapper.selectById(orderDetails.getItemId());
            int cost = itemDetails.getItemPrice();
            int beforePayBalance = userBalance.getBalance();
            if (beforePayBalance - cost < 0) {
                log.error("Balance not enough");
                throw new Exception("Balance not enough");
            }

            boolean isPaySuccess = balanceService.pay(userBalance, cost);
            if (!isPaySuccess) {
                log.error("pay failed");
                throw new Exception("pay failed");
            }

            LocalDateTime time = LocalDateTime.now();
            int year = time.getYear();
            int month = time.getMonthValue();
            int day = time.getDayOfMonth();
            int hour = time.getHour();
            int minute = time.getMinute();
            int second = time.getSecond();
            SnowflakeIdWorker snowflakeIdWorker = SnowflakeIdWorker.getInstance(7);
            long id = snowflakeIdWorker.nextId();
            String payNo = String.format("%d%d%d%d%d%d%d", year, month, day, hour, minute, second, id);
            orderDetails.setOrderStatus(OrderStatus.PAID);
            orderDetails.setPayNo(payNo);

            row = orderMapper.updateById(orderDetails);

            if(row == 0) {
                log.error("update order status fail");
                return 0;
            }

        }catch (Exception e) {
            e.printStackTrace();
            log.error("pay order fail!");
            return 0;
        }

        return row;
    }

    @Override
    public int updateOrder(OrderDetails orderDetails) {
        int row;
        try {
            row = orderMapper.updateById(orderDetails);
        }catch (Exception e) {
            e.printStackTrace();
            log.error("update order info fail");
            return 0;
        }
        return row;
    }

    @Override
    public List<OrderDetails> queryAllOrderAsSeller(String studentId) {
        log.info("start to query all order as seller");
        List<OrderDetails> res;
        QueryWrapper<OrderDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("seller_id", studentId);
        res = orderMapper.selectList(queryWrapper);
        return res;
    }

    @Override
    public List<OrderDetails> queryAllOrderAsBuyer(String studentId) {
        log.info("start to query all order as buyer");
        List<OrderDetails> res;
        QueryWrapper<OrderDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("buyer_id", studentId);
        res = orderMapper.selectList(queryWrapper);
        return res;
    }

    @Override
    public List<OrderDetails> queryAllOrder(String studentId) {
        log.info("start to query all order");
        List<OrderDetails> res;
        QueryWrapper<OrderDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("buyer_id", studentId).or().eq("seller_id", studentId);
        res = orderMapper.selectList(queryWrapper);
        return res;
    }

    @Override
    public OrderDetails queryDetails(String orderId) {
        log.info("start to query order details");
        OrderDetails orderDetails = orderMapper.selectById(orderId);
        log.info("query result: " + orderDetails);
        return orderDetails;
    }

    @Override
    public OrderDetails queryDetailsByItemId(String itemId) {
        QueryWrapper<OrderDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("item_id", itemId);
        OrderDetails orderDetails = orderMapper.selectList(queryWrapper).get(0);
        return orderDetails;
    }
}
