package com.btea.wxgoutmanage.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/16 16:20
 * @Description: 药品实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("t_medicine")
public class MedicineDO {

    /**
     * 药品id
     */
    private String medicineId;

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
    private String descriptionImageUrl;
}
