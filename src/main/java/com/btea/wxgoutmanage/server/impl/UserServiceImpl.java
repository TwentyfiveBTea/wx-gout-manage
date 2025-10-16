package com.btea.wxgoutmanage.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.btea.wxgoutmanage.common.constant.RedisCacheConstant;
import com.btea.wxgoutmanage.common.context.UserContext;
import com.btea.wxgoutmanage.common.convention.exception.ClientException;
import com.btea.wxgoutmanage.common.util.AliyunOssUtil;
import com.btea.wxgoutmanage.dao.entity.UserDO;
import com.btea.wxgoutmanage.dao.mapper.UserMapper;
import com.btea.wxgoutmanage.dto.req.*;
import com.btea.wxgoutmanage.server.UserService;
import com.btea.wxgoutmanage.vo.resp.SercurityQuestionRespVO;
import com.btea.wxgoutmanage.vo.resp.UserLoginRespVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/13 15:05
 * @Description: 用户接口实现层
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    private final StringRedisTemplate stringRedisTemplate;
    private final AliyunOssUtil aliyunOssUtil;
    private final UserMapper userMapper;

    /**
     * 用户注册
     *
     * @param requestParam 用户注册参数
     */
    @Override
    public void register(UserRegisterReqDTO requestParam) {
        log.info("开始用户注册，用户名: {}, 密码1: {}, 密码2:{}, 手机号: {}", requestParam.getUsername(), requestParam.getPassword1(), requestParam.getPassword2(), requestParam.getPhone());
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, requestParam.getUsername());
        if (userMapper.selectOne(queryWrapper) != null) {
            throw new ClientException("用户已存在");
        }
        if (!Objects.equals(requestParam.getPassword1(), requestParam.getPassword2())) {
            throw new ClientException("两次密码不一致");
        }
        UserDO userDO = UserDO.builder()
                .userid(UUID.randomUUID().toString())
                .username(requestParam.getUsername())
                .password(requestParam.getPassword1())
                .phone(requestParam.getPhone())
                .build();
        userMapper.insert(userDO);
    }

    /**
     * 账号密码登录
     *
     * @param requestParam 账号密码登录参数
     */
    @Override
    public UserLoginRespVO login(LoginReqDTO requestParam) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, requestParam.getUsername())
                .eq(UserDO::getPassword, requestParam.getPassword());
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ClientException("用户还未注册或密码错误");
        }
        String token = UUID.randomUUID().toString();
        UserContext.setUserId(userDO.getUserid());
        stringRedisTemplate.opsForValue().set(RedisCacheConstant.USER_LOGIN_CACHE_KEY + token, UserContext.getUserId(), 30, TimeUnit.DAYS);
        return new UserLoginRespVO(token);
    }

    /**
     * 设置安全问题
     *
     * @param requestParam 安全问题实体类
     */
    @Override
    public void setSecurityQuestion(SecurityQuestionReqDTO requestParam) {
        String currentUserId = UserContext.getUserId();
        UserDO userDO = new UserDO().builder()
                .securityQuestion(requestParam.getQuestion())
                .securityAnswer(requestParam.getAnswer())
                .build();
        LambdaUpdateWrapper<UserDO> updateWrapper = Wrappers.lambdaUpdate(UserDO.class)
                .eq(UserDO::getUserid, currentUserId);
        if (baseMapper.update(userDO, updateWrapper) != 1) {
            throw new ClientException("设置安全问题失败");
        }
    }

    /**
     * 修改安全问题
     *
     * @param requestParam 安全问题实体类
     */
    @Override
    public void updateSecurityQuestion(SecurityQuestionReqDTO requestParam) {
        String currentUserId = UserContext.getUserId();
        UserDO userDO = new UserDO().builder()
                .securityQuestion(requestParam.getQuestion())
                .securityAnswer(requestParam.getAnswer())
                .build();
        LambdaUpdateWrapper<UserDO> updateWrapper = Wrappers.lambdaUpdate(UserDO.class)
                .eq(UserDO::getUserid, currentUserId);
        if (baseMapper.update(userDO, updateWrapper) != 1) {
            throw new ClientException("修改安全问题失败");
        }
    }

    /**
     * 上传头像
     *
     * @param file 文件
     */
    @Override
    public void uploadAvatar(MultipartFile file) {
        String currentUserId = UserContext.getUserId();
        String avatarUrl = aliyunOssUtil.uploadAvatar(file, currentUserId);
        UserDO userDO = new UserDO().builder()
                .avatarUrl(avatarUrl)
                .build();
        LambdaUpdateWrapper<UserDO> updateWrapper = Wrappers.lambdaUpdate(UserDO.class)
                .eq(UserDO::getUserid, currentUserId);
        if (baseMapper.update(userDO, updateWrapper) != 1) {
            throw new ClientException("上传头像失败");
        }
    }

    /**
     * 修改头像
     *
     * @param file 文件
     */
    @Override
    public void updateAvatar(MultipartFile file) {
        String currentUserId = UserContext.getUserId();
        String avatarUrl = aliyunOssUtil.uploadAvatar(file, currentUserId);
        UserDO userDO = new UserDO().builder()
                .avatarUrl(avatarUrl)
                .build();
        LambdaUpdateWrapper<UserDO> updateWrapper = Wrappers.lambdaUpdate(UserDO.class)
                .eq(UserDO::getUserid, currentUserId);
        if (baseMapper.update(userDO, updateWrapper) != 1) {
            throw new ClientException("修改头像失败");
        }
    }

    /**
     * 根据用户名获取安全问题
     *
     * @param requestParam 安全问题实体类
     */
    @Override
    public SercurityQuestionRespVO getSecurityQuestionByUsername(ForgotPasswordReqDTO requestParam) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, requestParam.getUsername());
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ClientException("该账号不存在");
        }
        return new SercurityQuestionRespVO().builder()
                .securityQuestion(userDO.getSecurityQuestion())
                .userid(userDO.getUserid())
                .build();
    }

    /**
     * 重置密码
     *
     * @param requestParam 重置密码实体类
     */
    @Override
    public void resetPassword(ResetPasswordReqDTO requestParam) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUserid, requestParam.getUserid())
                .eq(UserDO::getSecurityAnswer, requestParam.getSecurityAnswer());
        if (baseMapper.selectOne(queryWrapper) == null) {
            throw new ClientException("安全问题回答错误");
        }
        if (!Objects.equals(requestParam.getPassword1(), requestParam.getPassword2())) {
            throw new ClientException("两次密码不一致");
        }
        UserDO userDO = new UserDO().builder()
                .password(requestParam.getPassword1())
                .build();
        LambdaUpdateWrapper<UserDO> updateWrapper = Wrappers.lambdaUpdate(UserDO.class)
                .eq(UserDO::getUserid, requestParam.getUserid());
        if (baseMapper.update(userDO, updateWrapper) != 1) {
            throw new ClientException("重置密码失败");
        }
    }
}
