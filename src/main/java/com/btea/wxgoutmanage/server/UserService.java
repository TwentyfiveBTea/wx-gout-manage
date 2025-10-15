package com.btea.wxgoutmanage.server;

import com.baomidou.mybatisplus.extension.service.IService;
import com.btea.wxgoutmanage.dao.entity.UserDO;
import com.btea.wxgoutmanage.dto.resp.LoginRespDTO;
import com.btea.wxgoutmanage.dto.resp.SecurityQuestionRespDTO;
import com.btea.wxgoutmanage.dto.resp.UserRegisterRespDTO;
import com.btea.wxgoutmanage.vo.req.UserLoginRespVO;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/13 15:04
 * @Description: 用户接口层
 */
public interface UserService extends IService<UserDO> {

    /**
     * 用户注册
     *
     * @param requestParam 用户注册参数
     */
    void register(UserRegisterRespDTO requestParam);

    /**
     * 用户密码登录
     *
     * @param requestParam 用户密码登录参数
     */
    UserLoginRespVO login(LoginRespDTO requestParam);

    /**
     * 设置安全问题
     *
     * @param requestParam 安全问题实体类
     */
    void setSecurityQuestion(SecurityQuestionRespDTO requestParam);

    /**
     * 修改安全问题
     *
     * @param requestParam 安全问题实体类
     */
    void updateSecurityQuestion(SecurityQuestionRespDTO requestParam);

}
