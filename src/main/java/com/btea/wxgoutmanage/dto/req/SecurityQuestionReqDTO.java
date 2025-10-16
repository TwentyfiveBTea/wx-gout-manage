package com.btea.wxgoutmanage.dto.req;

import lombok.Data;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/15 13:49
 * @Description: 安全问题实体类
 */
@Data
public class SecurityQuestionReqDTO {
    /**
     * 问题
     */
    private String question;

    /**
     * 答案
     */
    private String answer;
}
