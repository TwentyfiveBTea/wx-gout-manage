package com.btea.wxgoutmanage.controller;

import com.btea.wxgoutmanage.common.convention.result.Result;
import com.btea.wxgoutmanage.common.convention.result.Results;
import com.btea.wxgoutmanage.dto.resp.LoginRespDTO;
import com.btea.wxgoutmanage.dto.resp.SecurityQuestionRespDTO;
import com.btea.wxgoutmanage.dto.resp.UserRegisterRespDTO;
import com.btea.wxgoutmanage.server.UserService;
import com.btea.wxgoutmanage.server.impl.UserServiceImpl;
import com.btea.wxgoutmanage.vo.req.UserLoginRespVO;
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
    private final UserServiceImpl userServiceImpl;

    @PostMapping("/user/register")
    public Result<Void> register(@RequestBody @Valid UserRegisterRespDTO requestParam) {
        userService.register(requestParam);
        return Results.success();
    }

    @PostMapping("/user/login")
    public Result<UserLoginRespVO> login(@RequestBody @Valid LoginRespDTO requestParam) {
        return Results.success(userServiceImpl.login(requestParam));
    }

    @PostMapping("/user/set-security-question")
    public Result<Void> setSecurityQuestion(@RequestBody SecurityQuestionRespDTO requestParam) {
        userService.setSecurityQuestion(requestParam);
        return Results.success();
    }

    @PostMapping("/user/update-security-question")
    public Result<Void> updateSecurityQuestion(@RequestBody SecurityQuestionRespDTO requestParam) {
        userService.updateSecurityQuestion(requestParam);
        return Results.success();
    }
}
