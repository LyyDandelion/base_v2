package cn.stylefeng.guns.modular.app.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class TestVo {

    @NotBlank(message = "str不能为空")
    private String str;

    @Length(max = 3,message = "最大为3")
    @NotBlank(message = "len不能为空")
    private String len;

}
