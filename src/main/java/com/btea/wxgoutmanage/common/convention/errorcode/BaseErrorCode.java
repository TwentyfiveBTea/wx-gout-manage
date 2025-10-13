package com.btea.wxgoutmanage.common.convention.errorcode;

import lombok.AllArgsConstructor;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/13 12:09
 * @Description: 基础错误码
 */
@AllArgsConstructor
public enum BaseErrorCode implements IErrorCode {

    // 成功状态响应码
    SUCCESS("0", "成功"),

    // 客户端错误码
    SERVICE_ERROR("0000001", "服务端异常");

    // 用户注册登陆错误码


    private final String code;
    private final String message;

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
