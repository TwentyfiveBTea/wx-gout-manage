package com.btea.wxgoutmanage.dto.req;

import lombok.Data;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/16 12:13
 * @Description: 重置密码实体类
 */
@Data
public class ResetPasswordReqDTO {

    /**
     * 用户id
     */
    private String userid;

    /**
     * 安全问题答案
     */
    private String securityAnswer;

    /**
     * 新密码
     */
    private String password1;

    /**
     * 确认密码
     */
    private String password2;
}
