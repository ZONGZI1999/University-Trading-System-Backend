package com.zekizheng.trading.service;

import com.zekizheng.trading.entity.UserBalance;

/**
 * @author zongzi
 **/
public interface BalanceService {
    boolean pay(UserBalance userBalance, Integer cost);
    UserBalance getUserBalance(String studentId);
}
