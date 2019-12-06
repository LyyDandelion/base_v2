package cn.stylefeng.guns.modular.app.controller;


import cn.stylefeng.guns.modular.app.service.AppCarouselService;
import cn.stylefeng.guns.modular.base.util.Result;
import cn.stylefeng.roses.core.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 轮播图接口
 */
@Api("轮播图接口")
@RestController
@RequestMapping("/app/v1/carousel")
public class AppCarouselController extends BaseController {


    @Autowired
    AppCarouselService appCarouselService;

    /**
     * 获取轮播图列表
     * @return
     */
    @ApiOperation(value = "列表")
    @RequestMapping("/list")
    public Result list(){
        return  appCarouselService.getList();
    }

    @ApiOperation(value = "详情")
    @RequestMapping("/detail")
    public Result detail(Long carouselId){
        return appCarouselService.detail(carouselId);
    }

}
