package com.btea.wxgoutmanage.dto.req;

import lombok.Data;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/16 12:20
 * @Description: 重置密码实体类
 */
@Data
public class ForgotPasswordReqDTO {

    /**
     * 用户名
     */
    private String username;
}
