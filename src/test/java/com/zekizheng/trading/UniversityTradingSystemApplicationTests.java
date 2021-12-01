package com.zekizheng.trading;

import com.zekizheng.trading.entity.ItemDetails;
import com.zekizheng.trading.entity.OrderDetails;
import com.zekizheng.trading.mapper.ItemDetailsMapper;
import com.zekizheng.trading.mapper.OrderDetailsMapper;
import com.zekizheng.trading.mapper.StudentMapper;
import com.zekizheng.trading.status.ItemDetailsStatus;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UniversityTradingSystemApplicationTests {

    @Autowired
    private OrderDetailsMapper orderDetailsMapper;

    @Test
    void contextLoads() {
        OrderDetails orderDetails = new OrderDetails();
        orderDetailsMapper.insert(orderDetails);
    }

}
