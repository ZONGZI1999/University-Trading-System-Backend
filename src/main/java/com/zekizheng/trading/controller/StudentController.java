package com.zekizheng.trading.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.zekizheng.trading.dto.HttpBaseResponse;
import com.zekizheng.trading.dto.ResponseCode;
import com.zekizheng.trading.entity.Student;
import com.zekizheng.trading.mapper.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zongzi
 **/
@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/student")
@SaCheckLogin
public class StudentController {

    @Autowired
    private StudentMapper mapper;

    @GetMapping("/getByStudentId")
    public HttpBaseResponse<Student> queryStudentInfo(@RequestParam String studentId) {
        HttpBaseResponse<Student> resp = new HttpBaseResponse<>();
        Student res = mapper.selectById(studentId);
        if (res != null) {
            resp.setMessage(ResponseCode.SUCCESS);
            resp.setData(res);
            return resp;
        } else {
            resp.setMessage(ResponseCode.PARAM_ERROR);
            resp.setDescription("Student ID error!");
            return resp;
        }
    }
}
