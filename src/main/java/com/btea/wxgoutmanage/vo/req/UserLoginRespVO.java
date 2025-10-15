package com.btea.wxgoutmanage.vo.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/14 20:29
 * @Description: 用户登录返回参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginRespVO {

    /**
     * 登录token
     */
    private String token;
}
