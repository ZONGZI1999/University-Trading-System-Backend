package com.zekizheng.trading.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.zekizheng.trading.utils.SnowflakeIdWorker;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zongzi
 **/
@CrossOrigin(origins = "*")
@Controller
public class UploadController {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    @SaCheckLogin
    public Object upload(String name, @RequestParam(value = "multipartFile") MultipartFile multipartFile)
            throws IllegalStateException, IOException {
        Map<String, Object> map = new HashMap<>();
        if (multipartFile != null) {
            // 设置文件名称
            map.put("nameParam", name);
            // 设置文件名称
            map.put("fileame", multipartFile.getName());
            // 设置文件类型
            map.put("contentType", multipartFile.getContentType());
            // 设置文件大小
            map.put("fileSize", multipartFile.getSize());
            // 创建文件名称
            String fileName = UUID.randomUUID() + "."
                    + multipartFile.getContentType().substring(multipartFile.getContentType().lastIndexOf("/") + 1);
            // 获取到文件的路径信息
            String filePath = System.getProperty("user.dir") + "/image/" + fileName;
            // 打印保存路径
            System.out.println(filePath);
            // 保存文件的路径信息
            map.put("filePath", fileName);
            // 创建文件
            File saveFile = new File(filePath);
            // 文件保存
            multipartFile.transferTo(saveFile);
            // 返回信息
            return map;
        } else {
            return "no file ";
        }
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(@RequestParam String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取upload文件夹的路径
        String realPath = System.getProperty("user.dir") + "/image";
        //通过流读取文件
        FileInputStream is = new FileInputStream(new File(realPath, fileName));
        //获得响应流
        ServletOutputStream os = response.getOutputStream();
        //设置响应头信息
        response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode(fileName,"UTF-8"));
        //通过响应流将文件输入流读取到的文件写出
        IOUtils.copy(is,os);
        //关闭流
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);
    }

}
