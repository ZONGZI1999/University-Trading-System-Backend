package com.zekizheng.trading.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.zekizheng.trading.dto.HttpBaseResponse;
import com.zekizheng.trading.dto.ResponseCode;
import com.zekizheng.trading.entity.Student;
import com.zekizheng.trading.entity.UserBalance;
import com.zekizheng.trading.mapper.StudentMapper;
import com.zekizheng.trading.mapper.UserBalanceMapper;
import com.zekizheng.trading.service.BalanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zongzi
 **/
@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/account")
public class LoginController {

    @Autowired
    private UserBalanceMapper balanceMapper;

    @Autowired
    private StudentMapper studentMapper;

    @PostMapping("/login")
    public HttpBaseResponse<SaTokenInfo> login(@RequestBody Student student){
        HttpBaseResponse<SaTokenInfo> resp = new HttpBaseResponse<>();
        log.info(student.toString());
        if (student.getStudentId() == null || student.getStudentId().isEmpty()){
            resp.setMessage(ResponseCode.PARAM_ERROR);
            resp.setDescription("Student ID cannot be empty!");
            return resp;
        }
        if (student.getPassword() == null || student.getPassword().isEmpty()) {
            resp.setMessage(ResponseCode.PARAM_ERROR);
            resp.setDescription("Password cannot be empty!");
            return resp;
        }
        boolean isSignSuccess = false;
        try{
            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.get("https://app.xmu.edu.my/Maintenance/Account/Login")
                    .asString(); //GET
            String html = response.getBody();
            Document doc = Jsoup.parse(html, "utf-8");
            Elements element = doc.getElementsByAttributeValue("name", "__RequestVerificationToken");
            String token = element.attr("value");

            HttpResponse<String> response2 = Unirest.post("https://app.xmu.edu.my/Maintenance/Account/Login")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Cookie", response.getHeaders().get("Set-Cookie").get(0))
                    .field("__RequestVerificationToken", token)
                    .field("CampusID", student.getStudentId())
                    .field("Password", student.getPassword())
                    .asString();
            if (response2.getHeaders().get("Set-Cookie") != null) {
                Map<String, String> resultValue = new HashMap<>();
                for(String s:response2.getHeaders().get("Set-Cookie")){
                    String [] tmp = s.split(";");
                    String [] keyAndValue = tmp[0].split("=");
                    resultValue.put(keyAndValue[0], keyAndValue.length > 1 ? keyAndValue[1]:null);
                }
                String name = resultValue.get("Name");
                student.setStudentName(name);
                isSignSuccess = true;
            } else {
                System.out.println("Error!");
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        if (!isSignSuccess) {
            resp.setMessage(ResponseCode.PARAM_ERROR);
            resp.setDescription("Campus ID or Password is not correct!");
            return resp;
        }

        String studentId = student.getStudentId();
        StpUtil.login(studentId);
        resp.setMessage(ResponseCode.SUCCESS);
        resp.setData(StpUtil.getTokenInfo());

        if (balanceMapper.selectById(student.getStudentId()) == null){
            UserBalance userBalance = new UserBalance();
            userBalance.setStudentId(studentId);
            userBalance.setBalance(0);
            balanceMapper.insert(userBalance);
        }
        if (studentMapper.selectById(studentId) == null) {
            Student studentInfo = new Student();
            studentInfo.setStudentId(studentId);
            studentMapper.insert(studentInfo);
        }
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
