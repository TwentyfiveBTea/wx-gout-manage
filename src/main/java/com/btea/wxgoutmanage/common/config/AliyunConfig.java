package com.btea.wxgoutmanage.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/13 16:17
 * @Description: 阿里云配置类
 */
@Data
@Component
@ConfigurationProperties("aliyun.smscode")
public class AliyunConfig {

    /**
     * 阿里云 accessKeyId
     */
    private String accessKeyId;

    /**
     * 阿里云 accessKeySecret
     */
    private String accessKeySecret;

    /**
     * 阿里云云服务所在的数据中心地域
     */
    private String regionId;

    /**
     * 短信模板 ID
     */
    private String templateCode;
}
