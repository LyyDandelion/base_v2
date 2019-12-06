package cn.stylefeng.guns.modular.app.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Accessors(chain = true)
public class RegVo {

    /**
     * 用户名
     */
    @NotBlank(message = "账号不能为空")
    @Pattern(regexp = "[0-9]{11}",message = "账号格式有误")
    private String phone;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "[a-zA-Z0-9]{6,16}",message = "密码格式有误")
    private String password;

    /**
     * 确认密码
     */
    @NotBlank(message = "确认密码不能为空")
    @Pattern(regexp = "[a-zA-Z0-9]{6,16}",message = "密码格式有误")
    private String confirmPwd;

    /**
     * 安全密码
     */
    @NotBlank(message = "安全密码不能为空")
    @Pattern(regexp = "[0-9]{6}",message = "安全密码格式有误")
    private String security;





}
