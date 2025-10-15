package com.btea.wxgoutmanage.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.btea.wxgoutmanage.common.constant.RedisCacheConstant;
import com.btea.wxgoutmanage.common.context.UserContext;
import com.btea.wxgoutmanage.common.convention.exception.ClientException;
import com.btea.wxgoutmanage.dao.entity.UserDO;
import com.btea.wxgoutmanage.dao.mapper.UserMapper;
import com.btea.wxgoutmanage.dto.resp.LoginRespDTO;
import com.btea.wxgoutmanage.dto.resp.UserRegisterRespDTO;
import com.btea.wxgoutmanage.server.UserService;
import com.btea.wxgoutmanage.vo.req.UserLoginRespVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

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
    private final UserMapper userMapper;

    /**
     * 用户注册
     *
     * @param requestParam 用户注册参数
     */
    @Override
    public void register(UserRegisterRespDTO requestParam) {
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
    public UserLoginRespVO login(LoginRespDTO requestParam) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, requestParam.getUsername())
                .eq(UserDO::getPassword, requestParam.getPassword());
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ClientException("用户还未注册");
        }
        String token = UUID.randomUUID().toString();
        UserContext.setUserId(userDO.getUserid());
        stringRedisTemplate.opsForValue().set(RedisCacheConstant.USER_LOGIN_CACHE_KEY + token, UserContext.getUserId(), 30, TimeUnit.DAYS);
        return new UserLoginRespVO(token);
    }


}
