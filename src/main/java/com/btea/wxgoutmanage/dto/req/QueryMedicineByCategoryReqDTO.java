package com.btea.wxgoutmanage.dto.req;

import lombok.Data;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/16 17:54
 * @Description: 药品类别查询实体类
 */
@Data
public class QueryMedicineByCategoryReqDTO {

    /**
     * 药品类别
     */
    private String medicineCategory;
}
