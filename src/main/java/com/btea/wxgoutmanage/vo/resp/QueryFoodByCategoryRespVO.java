package com.btea.wxgoutmanage.vo.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/18 16:12
 * @Description: 查询食物类别返回实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryFoodByCategoryRespVO {

    /**
     * 食物名字
     */
    String foodName;

    /**
     * 食用类别
     */
    String edibleCategory;

    /**
     * 食物图片链接
     */
    String foodImageUrl;
}
