package com.zekizheng.trading.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.zekizheng.trading.dto.HttpBaseResponse;
import com.zekizheng.trading.dto.ResponseCode;
import com.zekizheng.trading.entity.UserBalance;
import com.zekizheng.trading.service.BalanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zongzi
 **/
@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/balance")
@SaCheckLogin
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @GetMapping("/queryBalance")
    public HttpBaseResponse<UserBalance> getUserBalance() {
        HttpBaseResponse<UserBalance> resp = new HttpBaseResponse<>();

        String studentId = StpUtil.getLoginIdAsString();

        UserBalance userBalance = balanceService.getUserBalance(studentId);

        if (userBalance == null) {
            resp.setMessage(ResponseCode.DATABASE_ERROR);
            return resp;
        }
        resp.setMessage(ResponseCode.SUCCESS);
        resp.setData(userBalance);
        return resp;
    }
}
