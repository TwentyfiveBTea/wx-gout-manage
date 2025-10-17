package com.btea.wxgoutmanage.vo.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/16 20:06
 * @Description: 药品返回实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryMedicineRespVO {

    /**
     * 药品名字
     */
    private String medicineName;

    /**
     * 药品类别
     */
    private String medicineCategory;

    /**
     * 药品描述
     */
    private String description;

    /**
     * 药品图片链接
     */
    private String medicineImageUrl;
}
