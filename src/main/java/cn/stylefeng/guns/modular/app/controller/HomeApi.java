package cn.stylefeng.guns.modular.app.controller;

import cn.stylefeng.guns.modular.app.service.HomeService;
import cn.stylefeng.guns.modular.app.service.MemberService;
import cn.stylefeng.guns.modular.app.vo.LoginVo;
import cn.stylefeng.guns.modular.app.vo.RegVo;
import cn.stylefeng.guns.modular.base.util.RedisUtil;
import cn.stylefeng.guns.modular.base.util.Result;
import cn.stylefeng.guns.modular.system.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * app入口
 */
@CrossOrigin
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


    /**
     * 退出登录
     */
    @PostMapping("/logout")
    public Result logout(@RequestHeader("token")String token)
    {
        return homeService.logout(token);
    }

    /**
     * 获取验证码
     * @param phone
     * @param type
     * @return
     */
    @PostMapping("/getMsg")
    public Result msg(@RequestHeader(value = "token",required = false,defaultValue = "-1") String token,@RequestParam(defaultValue = "1")String phone, @RequestParam(value = "type", required = false, defaultValue = "1") Long type){
        return homeService.getMsg(token,phone,type);
    }


}
