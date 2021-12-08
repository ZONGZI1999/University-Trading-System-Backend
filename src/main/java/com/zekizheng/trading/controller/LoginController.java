package com.zekizheng.trading.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.zekizheng.trading.dto.HttpBaseResponse;
import com.zekizheng.trading.dto.ResponseCode;
import com.zekizheng.trading.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author zongzi
 **/
@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/account")
public class LoginController {

    @PostMapping("/login")
    public HttpBaseResponse<SaTokenInfo> login(@RequestBody Student student){
        HttpBaseResponse<SaTokenInfo> resp = new HttpBaseResponse<>();
        log.info(student.toString());
        StpUtil.login(student.getStudentId());
        resp.setMessage(ResponseCode.SUCCESS);
        resp.setData(StpUtil.getTokenInfo());
        return resp;
    }

    @GetMapping("/valid")
    public HttpBaseResponse<Object> tokenIsValid(@RequestParam String token) {
        HttpBaseResponse<Object> resp = new HttpBaseResponse<>();
        Object studentObj = StpUtil.getLoginIdByToken(token);
        if (studentObj == null){
            resp.setMessage(ResponseCode.INTERNAL_ERROR);
            resp.setDescription("Your token is invalid. Please login again");
            return resp;
        } else {
            resp.setMessage(ResponseCode.SUCCESS);
            resp.setData(studentObj);
            return resp;
        }

    }


}
