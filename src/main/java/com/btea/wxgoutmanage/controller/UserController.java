package com.btea.wxgoutmanage.controller;

import com.btea.wxgoutmanage.common.convention.result.Result;
import com.btea.wxgoutmanage.common.convention.result.Results;
import com.btea.wxgoutmanage.dto.req.*;
import com.btea.wxgoutmanage.server.UserService;
import com.btea.wxgoutmanage.vo.resp.SercurityQuestionRespVO;
import com.btea.wxgoutmanage.vo.resp.UserLoginRespVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/user/register")
    public Result<Void> register(@RequestBody @Valid UserRegisterReqDTO requestParam) {
        userService.register(requestParam);
        return Results.success();
    }

    @PostMapping("/user/login")
    public Result<UserLoginRespVO> login(@RequestBody @Valid LoginReqDTO requestParam) {
        return Results.success(userService.login(requestParam));
    }

    @PostMapping("/user/set-security-question")
    public Result<Void> setSecurityQuestion(@RequestBody SecurityQuestionReqDTO requestParam) {
        userService.setSecurityQuestion(requestParam);
        return Results.success();
    }

    @PostMapping("/user/update-security-question")
    public Result<Void> updateSecurityQuestion(@RequestBody SecurityQuestionReqDTO requestParam) {
        userService.updateSecurityQuestion(requestParam);
        return Results.success();
    }

    @PostMapping("/user/upload-avatar")
    public Result<Void> uploadAvatar(@RequestParam("file") MultipartFile file) {
        userService.uploadAvatar(file);
        return Results.success();
    }

    @PostMapping("/user/update-avatar")
    public Result<Void> updateAvatar(@RequestParam("file") MultipartFile file) {
        userService.updateAvatar(file);
        return Results.success();
    }

    @PostMapping("/user/forget-password")
    public Result<SercurityQuestionRespVO> forgetPassword(@RequestBody ForgotPasswordRepDTO requestParam) {
        return Results.success(userService.getSecurityQuestionByUsername(requestParam));
    }

    @PostMapping("/user/reset-password")
    public Result<Void> resetPassword(@RequestBody ResetPasswordReqDTO requestParam) {
        userService.resetPassword(requestParam);
        return Results.success();
    }
}
