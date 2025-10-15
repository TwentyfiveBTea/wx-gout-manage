package com.btea.wxgoutmanage.common.config;

import com.btea.wxgoutmanage.common.interceptor.TokenInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/14 19:51
 * @Description: Web配置类（拦截器）
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册Token拦截器
        registry.addInterceptor(tokenInterceptor)
                // 拦截所有请求
                .addPathPatterns("/**");
                // 注意：TokenInterceptor内部已经处理了白名单逻辑，不需要在这里排除
    }
}