package cn.stylefeng.guns.modular.app.controller;

import cn.stylefeng.guns.modular.app.service.HomeService;
import cn.stylefeng.guns.modular.app.service.MemberService;
import cn.stylefeng.guns.modular.app.vo.LoginVo;
import cn.stylefeng.guns.modular.app.vo.RegVo;
import cn.stylefeng.guns.modular.app.vo.VersionVo;
import cn.stylefeng.guns.modular.base.util.RedisUtil;
import cn.stylefeng.guns.modular.base.util.Result;
import cn.stylefeng.guns.modular.system.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * app入口
 */

@RestController
@RequestMapping(value = "/api")
public class HomeApi {



    @Autowired
    RedisUtil redisUtil;

    @Autowired
    MemberService memberService;


    @Autowired
    LoginLogService loginLogService;


    @Autowired
    HomeService homeService;

    /**
     * 注册
     * @param regVo
     * @return
     */
    @PostMapping("/register")
    public Result register(@Valid RegVo regVo) {
        return homeService.register(regVo);
    }

    /**
     *  登录
     * @param loginVo
     * @return
     */
    @PostMapping("/login")
    public Result login(@Valid LoginVo loginVo) throws Exception{
        return homeService.login(loginVo);
    }

    /**
     * 忘记密码
     * @return
     */
    @PostMapping("/forgetPwd")
    public Result forgetPwd(@Valid RegVo forgetVo){
        return homeService.forgetPwd(forgetVo);
    }


    /***
     * 用户协议
     */
    @PostMapping("/declares")
    public Result declares()
    {
        return homeService.declares();
    }

    /**
     * 测试 获取用户信息
     */
    @RequestMapping("/getInfo")
    public Result getInfo(String openId){

        return Result.fail(404,"空");
    }

    /**
     * 版本信息
     */
    @PostMapping("/version")
    public Result version(VersionVo versionVo){
        return homeService.version(versionVo);
    }


    /**
     * 退出登录
     */
    @PostMapping("/logout")
    public Result logout(@RequestHeader("token")String token)
    {
        return homeService.logout(token);
    }



}
