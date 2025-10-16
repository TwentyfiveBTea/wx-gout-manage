package com.btea.wxgoutmanage.vo.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/16 17:22
 * @Description: 查询药品类别返回实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryMedicineByCategoryRespVO {

    /**
     * 药品名字
     */
    private String medicineName;

    /**
     * 药品描述
     */
    private String description;

    /**
     * 药品图片链接
     */
    private String descriptionImageUrl;
}
