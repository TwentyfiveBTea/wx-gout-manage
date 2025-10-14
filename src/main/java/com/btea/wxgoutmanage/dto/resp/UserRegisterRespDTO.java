package com.btea.wxgoutmanage.dto.resp;

import com.btea.wxgoutmanage.common.serialize.PhoneDesensitizationSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import javax.validation.constraints.Pattern;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/13 15:52
 * @Description: 用户注册实体类
 */
@Data
public class UserRegisterRespDTO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码1
     */
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$",
            message = "密码必须包含大小写字母和数字，且不少于6位")
    private String password1;

    /**
     * 密码2
     */
    private String password2;

    /**
     * 手机号
     */
    @JsonSerialize(using = PhoneDesensitizationSerializer.class)
    private String phone;

    /**
     * 验证码
     */
    private String code;

}
