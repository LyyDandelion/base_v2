package cn.stylefeng.guns.modular.app.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Accessors(chain = true)
public class RegVo {

    /**
     * 用户名
     */
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1\\d{10}$",message = "账号格式有误")
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
    @Pattern(regexp = "[a-zA-Z0-9]{6,16}",message = "确认密码格式有误")
    private String confirmPwd;



    @NotBlank(message = "邀请码不能为空")
    private String inviteCode;


    @NotBlank(message = "手机验证码不为空")
    private String msg;

    @NotNull(message = "省不能为空")
    private Long provinceId;

    @NotNull(message = "市不能为空")
    private Long cityId;

    @NotNull(message = "区/县不能为空")
    private Long areaId;

}
