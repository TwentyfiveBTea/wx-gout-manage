package com.btea.wxgoutmanage.dto.req;

import lombok.Data;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/13 15:33
 * @Description: 账号密码登陆实体类
 */
@Data
public class LoginReqDTO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
