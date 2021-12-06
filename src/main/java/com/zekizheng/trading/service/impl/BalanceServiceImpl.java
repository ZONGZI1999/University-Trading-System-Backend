package com.zekizheng.trading.service.impl;

import com.zekizheng.trading.entity.UserBalance;
import com.zekizheng.trading.mapper.UserBalanceMapper;
import com.zekizheng.trading.service.BalanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zongzi
 **/
@Slf4j
@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class BalanceServiceImpl implements BalanceService {

    @Autowired
    private UserBalanceMapper balanceMapper;

    @Override
    public boolean pay(UserBalance userBalance, Integer cost) {
        if (userBalance == null) {
            String errMsg = "userBalance obj is null";
            log.error(errMsg);
            throw new IllegalArgumentException(errMsg);
        }
        String studentId = userBalance.getStudentId();
        UserBalance userBalanceResult = balanceMapper.selectById(studentId);
        Integer nowBalance = userBalanceResult.getBalance();

        // 预扣款
        if (nowBalance - cost < 0) {
            String errMsg = "balance is not enough";
            log.error(errMsg);
            throw new IllegalArgumentException(errMsg);
        }

        userBalance.setBalance(nowBalance - cost);

        try {
            balanceMapper.updateById(userBalance);
        }catch (Exception e) {
            e.printStackTrace();
            log.error("update new user balance fail, rollback!");
            throw e;
        }

        return true;
    }

    @Override
    public UserBalance getUserBalance(String studentId) {
        UserBalance userBalance = null;
        try{
            userBalance = balanceMapper.selectById(studentId);
        }catch (Exception e) {
            e.printStackTrace();
            log.error("get user balance fail");
            throw e;
        }
        return userBalance;
    }
}
