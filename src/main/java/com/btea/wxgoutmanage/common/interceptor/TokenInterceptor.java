package com.btea.wxgoutmanage.common.interceptor;

import com.btea.wxgoutmanage.common.context.UserContext;
import com.btea.wxgoutmanage.common.convention.exception.ClientException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/14 19:48
 * @Description: Token验证拦截器
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TokenInterceptor implements HandlerInterceptor {

    private final StringRedisTemplate stringRedisTemplate;

    private static final String TOKEN_PREFIX = "USER_TOKEN:";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            // 从请求头中获取token
            String token = request.getHeader("Authorization");
            // 检查token是否存在
            if (!StringUtils.hasText(token)) {
                throw new ClientException("未提供认证令牌");
            }
            // 检查token是否有效（在Redis中是否存在）
            String userId = stringRedisTemplate.opsForValue().get(TOKEN_PREFIX + token);
            if (Objects.isNull(userId)) {
                throw new ClientException("认证令牌已过期或无效");
            }
            // 设置用户上下文
            UserContext.setUserId(userId);
            return true;
        } catch (Exception e) {
            log.error("Token验证失败", e);
            throw e;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清除用户上下文，防止内存泄漏
        UserContext.clear();
    }
}
