package cn.stylefeng.guns.modular.app.controller;

import cn.stylefeng.guns.modular.app.service.HomeService;
import cn.stylefeng.guns.modular.app.vo.VersionVo;
import cn.stylefeng.guns.modular.base.util.Result;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公共API
 * 无需token
 */
@RequestMapping("/api/common")
@RestController
public class CommonApi {

    @Autowired
    HomeService homeService;
    /***
     * 用户协议
     */
    @PostMapping("/declares")
    public Result declares()
    {
        return homeService.declares();
    }


    /**
     * 版本信息
     */
    @PostMapping("/version")
    public Result version(VersionVo versionVo){
        return homeService.version(versionVo);
    }


    /**
     * 省市区列表
     */
    @PostMapping("/areaList")
    public Result areaList(@RequestParam(defaultValue = "1") int level,@RequestParam(defaultValue = "-1") int id){
        return homeService.areaList(level,id);
    }

}
