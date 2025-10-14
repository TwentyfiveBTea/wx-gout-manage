package com.btea.wxgoutmanage.dto.resp;

import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/13 18:55
 * @Description: 短信验证码手机实体类
 */
@Data
public class SmsCodePhoneRespDTO {
    /**
     * 手机号
     */
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;
}
