package com.btea.wxgoutmanage.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/17 13:52
 * @Description: 食物实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("t_food")
public class FoodDO {

    /**
     * 食物id
     */
    private String foodId;

    /**
     * 食物名
     */
    private String foodName;

    /**
     * 嘌呤含量
     */
    private String purineContent;

    /**
     * 食用类别
     */
    private String edibleCategory;

    /**
     * 食物图片链接
     */
    private String foodImageUrl;
}
