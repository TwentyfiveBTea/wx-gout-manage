package com.btea.wxgoutmanage.controller;

import com.btea.wxgoutmanage.common.convention.result.Result;
import com.btea.wxgoutmanage.common.convention.result.Results;
import com.btea.wxgoutmanage.common.util.SmsCodeUtil;
import com.btea.wxgoutmanage.dto.resp.SmsCodePhoneRespDTO;
import com.btea.wxgoutmanage.dto.resp.UserRegisterRespDTO;
import com.btea.wxgoutmanage.server.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/13 15:14
 * @Description: 用户控制层
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SmsCodeUtil smsCodeUtil;

    @PostMapping("user/register")
    public Result<Void> register(@RequestBody @Valid UserRegisterRespDTO requestParam) {
        userService.register(requestParam);
        return Results.success();
    }

    @PostMapping("user/send-generateCode")
    public Result<Void> sendGenerateCode(@RequestBody @Valid SmsCodePhoneRespDTO requestParam) {
        smsCodeUtil.sendSmsCode(requestParam.getPhone(), SmsCodeUtil.generateSixDigitCode());
        return Results.success();
    }
}
