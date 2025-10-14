package com.btea.wxgoutmanage.server.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.btea.wxgoutmanage.common.constant.RedisCacheConstant;
import com.btea.wxgoutmanage.common.convention.exception.ClientException;
import com.btea.wxgoutmanage.dao.entity.UserDO;
import com.btea.wxgoutmanage.dao.mapper.UserMapper;
import com.btea.wxgoutmanage.dto.resp.UserRegisterRespDTO;
import com.btea.wxgoutmanage.server.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/13 15:05
 * @Description: 用户接口实现层
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    private final UserMapper userMapper;
    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 用户注册
     *
     * @param requestParam 用户注册参数
     */
    @Override
    public void register(UserRegisterRespDTO requestParam) {
        log.info("开始用户注册，用户名: {}, 密码1: {}, 密码2:{}, 手机号: {}, 验证码: {}", requestParam.getUsername(), requestParam.getPassword1(), requestParam.getPassword2(), requestParam.getPhone(), requestParam.getCode());
        if (userMapper.selectOne(null) != null) {
            throw new ClientException("用户已存在");
        }
        if (!Objects.equals(requestParam.getPassword1(), requestParam.getPassword2())) {
            throw new ClientException("两次密码不一致");
        }
        stringRedisTemplate.opsForValue().get(RedisCacheConstant.SMS_CODE_CACHE_KEY + requestParam.getPhone());
        if (!Objects.equals(requestParam.getCode(), stringRedisTemplate.opsForValue().get(RedisCacheConstant.SMS_CODE_CACHE_KEY + requestParam.getPhone()))) {
            throw new ClientException("验证码错误");
        }

        UserDO userDO = UserDO.builder()
                .username(requestParam.getUsername())
                .password(requestParam.getPassword1())
                .phone(requestParam.getPhone())
                .build();
        userMapper.insert(userDO);
    }
}
