package com.btea.wxgoutmanage.dto.req;

import lombok.Data;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/18 19:24
 * @Description: 查询食品参数
 */
@Data
public class QueryFoodReqDTO {

    /**
     * 食品名字
     */
    private String foodName;
}
