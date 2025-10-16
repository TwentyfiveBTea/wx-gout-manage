package com.btea.wxgoutmanage.dto.req;

import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @Author: TwentyFiveBTea
 * @Date: 2025/10/15 15:51
 * @Description: 修改密码实体类
 */
@Data
public class UpdatePasswordReqDTO {
    /**
     * 新密码
     */
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$",
            message = "密码必须包含大小写字母和数字，且不少于6位")
    private String password1;

    /**
     * 确认密码
     */
    private String password2;
}
