package com.btea.wxgoutmanage.dto.req;

import lombok.Data;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/17 14:10
 * @Description: 食用类别查询实体类
 */
@Data
public class QueryFoodByCategoryReqDTO {

    /**
     * 食用类别
     */
    private String edibleCategory;
}
