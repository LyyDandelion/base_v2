package cn.stylefeng.guns.modular.app.controller;


import cn.stylefeng.guns.core.common.annotion.ApiGateway;
import cn.stylefeng.guns.modular.app.service.PersonalService;
import cn.stylefeng.guns.modular.app.vo.TestVo;
import cn.stylefeng.guns.modular.base.util.Result;
import cn.stylefeng.roses.core.base.controller.BaseController;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 外部回调统一Api
 *
 * 注：当前token未拦截
 */
@RestController
@RequestMapping("/api/personal")
public class PersonalController {

    @Autowired
    PersonalService personalService;

    /**
     * 联系客服
     */
    @RequestMapping("/contact")
    public Result contact()
    {
        return  personalService.contact();
    }


    /**
     * 业务文章
     * @param articleType
     * @return
     */
    @RequestMapping("/article")
    public Result article(String articleType)
    {
        return personalService.getArticle(articleType);
    }


    /**
     * notifyUrl
     *
     * @return
     */
    @ApiOperation(value = "notifyUrl - 测试")
    @PostMapping("/notifyUrl")
    @ApiGateway(code = "A10001")
    public String notifyUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String success = "success";
        System.err.println("回调监听：" + response.toString());
        Map<String, String> retMap = new HashMap<>();

        Set<Map.Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();

        for (Map.Entry<String, String[]> entry : entrySet) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            int valLen = values.length;

            if (valLen == 1) {
                retMap.put(name, values[0]);
            } else if (valLen > 1) {
                StringBuilder sb = new StringBuilder();
                for (String val : values) {
                    sb.append(",").append(val);
                }
                retMap.put(name, sb.toString().substring(1));
            } else {
                retMap.put(name, "");
            }
        }
        System.out.println("map:"+retMap.toString());
//        return success;
        return null;
    }

    /**
     * notifyUrl
     *
     * @return
     */
    @ApiOperation(value = "notifyUrl - 测试")
    @PostMapping("/notifyUrlBean")
    @ApiGateway(code = "A10002")
    public String notifyUrlBean(String str) {
        String success = "success";
        System.err.println("回调监听：" + str.toString());
//        return success;
        return null;
    }

    @PostMapping("/hello")
    public Result hello(@Valid TestVo testVo){
        return Result.success("成功");
    }


}
