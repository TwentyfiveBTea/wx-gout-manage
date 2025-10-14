package com.btea.wxgoutmanage.common.convention.exception;

import com.btea.wxgoutmanage.common.convention.errorcode.BaseErrorCode;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/14 10:19
 * @Description: 客户端异常
 */
public class ClientException extends AbstractException{

    public ClientException(String message) {
        super(message, null, BaseErrorCode.CLIENT_ERROR);
    }

    public ClientException(String message, Throwable throwable) {
        super(message, throwable, BaseErrorCode.CLIENT_ERROR);
    }
}
