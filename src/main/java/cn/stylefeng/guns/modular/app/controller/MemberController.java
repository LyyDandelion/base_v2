package cn.stylefeng.guns.modular.app.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.validation.BindingResult;
import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.Const;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.Map;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.guns.modular.app.service.MemberService;
import cn.stylefeng.guns.modular.app.wrapper.MemberWrapper;
import cn.stylefeng.guns.modular.app.entity.Member;
import cn.stylefeng.guns.modular.app.constant.MemberMap;

/**
 * 用户信息控制器
 *
 * @author yaying.liu
 * @Date 2019-12-06 09:50:49
 */
@Controller
@RequestMapping("/member")
public class MemberController extends BaseController {

    private String PREFIX = "/modular/app/member/";

     @Autowired
     private MemberService memberService;

    /**
     * 跳转到用户信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "member.html";
    }

    /**
     * 跳转到添加用户信息
     */
    @RequestMapping("/member_add")
    public String memberAdd() {
        return PREFIX + "member_add.html";
    }

    /**
     * 跳转到修改用户信息
     */
    @RequestMapping("/member_edit")
    public String memberEdit(Long memberId, Model model) {
        Member condition = this.memberService.getById(memberId);
        model.addAllAttributes(BeanUtil.beanToMap(condition));
        LogObjectHolder.me().set(condition);
        return PREFIX + "member_edit.html";
    }

    /**
     * 用户信息详情
     */
    @RequestMapping(value = "/detail/{memberId}")
    @ResponseBody
    public Object detail(@PathVariable("memberId") Long memberId) {
        Member entity = memberService.getById(memberId);
      //  MemberDto conditionDto = new MemberDto();
      //  BeanUtil.copyProperties(entity, conditionDto);
      //  return conditionDto;
        return entity;
    }


    /**
     * 查询用户信息列表
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String condition) {
        //根据条件查询
        Page<Map<String, Object>> result = memberService.selectByCondition(condition);
        Page wrapped = new MemberWrapper(result).wrap();
        return LayuiPageFactory.createPageInfo(wrapped);
    }

    /**
     * 编辑用户信息
     */
    @RequestMapping("/edit")
    @BussinessLog(value = "编辑参数", key = "memberId", dict = MemberMap.class)
    @ResponseBody
    public ResponseData edit(Member member) {
        memberService.updateById(member);
        return SUCCESS_TIP;
    }

    /**
     * 添加用户信息
     */
    @RequestMapping("/add")
    @BussinessLog(value = "添加用户信息", key = "name", dict = MemberMap.class)
    @ResponseBody
    public ResponseData add(Member member, BindingResult result) {
        if (result.hasErrors()) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        if (member == null) {
            return ResponseData.error("参数不能为空");
        }
        this.memberService.addMember(member);
        return SUCCESS_TIP;
    }

    /**
     * 删除用户信息
     */
    @RequestMapping("/delete")
    @BussinessLog(value = "删除用户信息", key = "memberId", dict = MemberMap.class)
    @ResponseBody
    public ResponseData delete(@RequestParam Long memberId) {
        if (ToolUtil.isEmpty(memberId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.memberService.deleteMember(memberId);
        return SUCCESS_TIP;
    }

    /**
     * 查询内容详情
     */
    @RequestMapping("/content/{memberId}")
    @ResponseBody
    public Object content(@PathVariable("memberId") Long id) {
        Member member = memberService.getById(id);
        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(member);
        return super.warpObject(new MemberWrapper(stringObjectMap));
    }
}