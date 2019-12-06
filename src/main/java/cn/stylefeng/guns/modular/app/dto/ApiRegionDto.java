package cn.stylefeng.guns.modular.app.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ApiRegionDto {

    private Long regionId;
    private String name;
    private int levelType;
}
