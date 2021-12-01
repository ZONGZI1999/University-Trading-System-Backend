package com.zekizheng.trading.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zongzi
 **/
@Data
@TableName
public class UserBalance {
    @TableId(type = IdType.INPUT)
    private String studentId;
    private Integer balance;
}
