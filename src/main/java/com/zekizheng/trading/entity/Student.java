package com.zekizheng.trading.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zongzi
 **/
@Data
@TableName(value = "student")
public class Student {
    @TableId
    private String studentId;
    private String studentName;
    @TableField(exist = false)
    private String password;
}
