package com.zekizheng.trading.config;

import cn.dev33.satoken.exception.NotLoginException;
import com.zekizheng.trading.dto.HttpBaseResponse;
import com.zekizheng.trading.dto.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zongzi
 **/
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = NotLoginException.class)
    @ResponseBody
    public HttpBaseResponse<String> tokenException(HttpServletRequest req, NotLoginException e){
        HttpBaseResponse<String> resp = new HttpBaseResponse<>();
        resp.setMessage(ResponseCode.LOGIN_STATUS_ERROR);
        resp.setData(e.getMessage());
        log.error(e.toString());
        return resp;
    }

    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public HttpBaseResponse<String> nullPointerException(HttpServletRequest req, NullPointerException e){
        HttpBaseResponse<String> resp = new HttpBaseResponse<>();
        resp.setMessage(ResponseCode.INTERNAL_ERROR);
        resp.setData(e.getMessage());
        log.error(e.toString());
        return resp;
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public HttpBaseResponse<String> missingParam(HttpServletRequest req, MissingServletRequestParameterException e) {
        HttpBaseResponse<String> resp = new HttpBaseResponse<>();
        resp.setMessage(ResponseCode.INTERNAL_ERROR);
        resp.setDescription(e.getMessage());
        log.error(e.toString());
        return resp;
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public HttpBaseResponse<String> paramTypeException(HttpServletRequest req, MethodArgumentTypeMismatchException e) {
        HttpBaseResponse<String> resp = new HttpBaseResponse<>();
        resp.setMessage(ResponseCode.INTERNAL_ERROR);
        resp.setDescription(e.getMessage());
        log.error(e.toString());
        return resp;
    }


}
