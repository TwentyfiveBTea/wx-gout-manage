package com.btea.wxgoutmanage.vo.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/16 12:16
 * @Description: 安全问题返回值
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SercurityQuestionRespVO {

    /**
     * 用户id
     */
    private String userid;

    /**
     * 安全问题
     */
    private String securityQuestion;
}
