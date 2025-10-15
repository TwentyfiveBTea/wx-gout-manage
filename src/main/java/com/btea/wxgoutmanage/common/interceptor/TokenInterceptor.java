package com.btea.wxgoutmanage.common.interceptor;

import com.btea.wxgoutmanage.common.constant.RedisCacheConstant;
import com.btea.wxgoutmanage.common.context.UserContext;
import com.btea.wxgoutmanage.common.convention.exception.ClientException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    // 白名单路径
    private final String[] whiteList = {
            "/user/register",
            "/user/login"
    };

    /**
     * 请求处理之前执行
     *
     * @param request  请求对象
     * @param response 响应对象
     * @param handler  处理器对象
     * @return 是否继续处理请求
     * @throws Exception 处理异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        // 检查是否在白名单中
        for (String pattern : whiteList) {
            if (pathMatcher.match(pattern, requestURI)) {
                log.info("白名单路径，跳过认证: {}", requestURI);
                return true;
            }
        }

        try {
            // 从请求头中获取token
            String token = request.getHeader("Authorization");
            // 检查token是否存在
            if (!StringUtils.hasText(token)) {
                throw new ClientException("未提供认证令牌");
            }
            // 检查token是否有效（在Redis中是否存在）
            String userId = stringRedisTemplate.opsForValue().get(RedisCacheConstant.USER_LOGIN_CACHE_KEY + token);
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

    /**
     * 请求处理完成之后执行
     *
     * @param request  请求对象
     * @param response 响应对象
     * @param handler  处理器对象
     * @param ex       异常对象
     * @throws Exception 处理异常
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清除用户上下文，防止内存泄漏
        UserContext.clear();
    }
}