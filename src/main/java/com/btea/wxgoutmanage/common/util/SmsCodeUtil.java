package com.btea.wxgoutmanage.common.util;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.btea.wxgoutmanage.common.config.AliyunConfig;
import com.btea.wxgoutmanage.common.constant.RedisCacheConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/13 16:46
 * @Description: sms验证码工具类
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SmsCodeUtil {

    private final AliyunConfig aliyunConfig;
    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 生成6位数字验证码
     *
     * @return 6位数字验证码字符串
     */
    public static String generateSixDigitCode() {
        Random random = new Random();
        // 生成一个6位数的随机数（范围在100000到999999之间）
        int code = random.nextInt(900000) + 100000;
        return String.valueOf(code);
    }

    /**
     * 创建阿里云客户端
     *
     * @return 阿里云客户端
     * @throws Exception 创建客户端时可能出现的异常
     */
    public Client createClient() throws Exception {
        Config config = new Config()
                .setAccessKeyId(aliyunConfig.getAccessKeyId())
                .setAccessKeySecret(aliyunConfig.getAccessKeySecret())
                .setRegionId(aliyunConfig.getRegionId());
        return new Client(config);
    }

    /**
     * 发送短信验证码
     * @param phone 手机号
     * @param generateCode 验证码
     */
    public void sendSmsCode(String phone, String generateCode) {
        SendSmsRequest request = new SendSmsRequest()
                .setPhoneNumbers(phone)
                .setSignName("验证码短信")
                .setTemplateCode(aliyunConfig.getTemplateCode())
                .setTemplateParam("{\"code\":\"您的验证码为：" + generateCode + "，请勿泄露于他人！\"}");
        try {
            Client client = createClient();
            log.info("开始发送短信验证码，手机号: {}, 验证码: {}", phone, generateCode);
            SendSmsResponse sendSmsResponse = client.sendSms(request);
            if(!"OK".equals(sendSmsResponse.getBody().getCode())){
                log.error("发送短信验证码失败，手机号: {}, 验证码: {}, 错误信息: {}", phone, generateCode, sendSmsResponse.getBody().getMessage());
                throw new RuntimeException(sendSmsResponse.getBody().getMessage());
            }
            log.info("发送短信验证码成功，手机号: {}, 验证码: {}", phone, generateCode);
            // 将验证码写入Redis缓存，限时一分钟
            stringRedisTemplate.opsForValue().set(RedisCacheConstant.SMS_CODE_CACHE_KEY + phone, generateCode, 1, TimeUnit.MINUTES);
            log.info("将验证码写入Redis缓存，手机号: {}, 验证码: {}", phone, generateCode);
        } catch (Exception e) {
            log.error("发送短信验证码失败，手机号: {}, 验证码: {}, 错误信息: {}", phone, generateCode, e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
