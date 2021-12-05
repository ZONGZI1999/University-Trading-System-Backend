package com.zekizheng.trading.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.zekizheng.trading.dto.HttpBaseResponse;
import com.zekizheng.trading.dto.ResponseCode;
import com.zekizheng.trading.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zongzi
 **/
@Slf4j
@RestController
@RequestMapping("/account")
public class LoginController {

    @PostMapping("/login")
    public HttpBaseResponse<String> login(@RequestBody Student student){
        HttpBaseResponse<String> resp = new HttpBaseResponse<>();
        log.info(student.toString());
        StpUtil.login(student.getStudentId());
        resp.setMessage(ResponseCode.SUCCESS);
        resp.setData(StpUtil.getTokenValueByLoginId(student.getStudentId()));
        return resp;
    }


}
