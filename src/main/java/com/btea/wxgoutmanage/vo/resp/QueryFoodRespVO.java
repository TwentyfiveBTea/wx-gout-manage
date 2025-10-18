package com.btea.wxgoutmanage.vo.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/18 19:27
 * @Description: 食品返回实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryFoodRespVO {

    /**
     * 食品名字
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
