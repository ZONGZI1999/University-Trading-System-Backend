package com.zekizheng.trading.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.GsonTypeHandler;
import com.zekizheng.trading.status.ItemDetailsStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author zongzi
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(autoResultMap = true)
public class ItemDetails implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer itemId;
    private String sellerId;
    private String itemTitle;
    private Integer itemPrice;
    @TableField(typeHandler = GsonTypeHandler.class)
    private List<String> itemDescription;
    @TableField(typeHandler = GsonTypeHandler.class)
    private List<String> itemImage;
    @TableField(typeHandler = GsonTypeHandler.class)
    private ItemDetailsStatus itemStatus;
    private Date createTime;
}
