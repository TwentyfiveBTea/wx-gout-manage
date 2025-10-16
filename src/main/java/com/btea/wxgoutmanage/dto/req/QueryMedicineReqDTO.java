package com.btea.wxgoutmanage.dto.req;

import lombok.Data;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/16 20:07
 * @Description: 查询药品参数
 */
@Data
public class QueryMedicineReqDTO {

    /**
     * 药品名字
     */
    private String medicineName;
}
