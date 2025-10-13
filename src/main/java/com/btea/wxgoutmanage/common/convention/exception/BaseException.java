package com.btea.wxgoutmanage.common.convention.exception;

import lombok.Getter;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/13 12:36
 * @Description: 基础异常类
 */
@Getter
public class BaseException extends RuntimeException {
    private final String code;

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(String message) {
        super(message);
        this.code = null;
    }
}
