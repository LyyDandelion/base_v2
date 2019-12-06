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
import cn.stylefeng.guns.modular.app.service.MemberRoleService;
import cn.stylefeng.guns.modular.app.wrapper.MemberRoleWrapper;
import cn.stylefeng.guns.modular.app.entity.MemberRole;
import cn.stylefeng.guns.modular.app.constant.MemberRoleMap;

/**
 * 用户角色关系控制器
 *
 * @author yaying.liu
 * @Date 2019-12-06 10:18:16
 */
@Controller
@RequestMapping("/memberRole")
public class MemberRoleController extends BaseController {

    private String PREFIX = "/modular/app/memberRole/";

     @Autowired
     private MemberRoleService memberRoleService;

    /**
     * 跳转到用户角色关系首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "memberRole.html";
    }

    /**
     * 跳转到添加用户角色关系
     */
    @RequestMapping("/memberRole_add")
    public String memberRoleAdd() {
        return PREFIX + "memberRole_add.html";
    }

    /**
     * 跳转到修改用户角色关系
     */
    @RequestMapping("/memberRole_edit")
    public String memberRoleEdit(Long memberRoleId, Model model) {
        MemberRole condition = this.memberRoleService.getById(memberRoleId);
        model.addAllAttributes(BeanUtil.beanToMap(condition));
        LogObjectHolder.me().set(condition);
        return PREFIX + "memberRole_edit.html";
    }

    /**
     * 用户角色关系详情
     */
    @RequestMapping(value = "/detail/{memberRoleId}")
    @ResponseBody
    public Object detail(@PathVariable("memberRoleId") Long memberRoleId) {
        MemberRole entity = memberRoleService.getById(memberRoleId);
      //  MemberRoleDto conditionDto = new MemberRoleDto();
      //  BeanUtil.copyProperties(entity, conditionDto);
      //  return conditionDto;
        return entity;
    }


    /**
     * 查询用户角色关系列表
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String condition) {
        //根据条件查询
        Page<Map<String, Object>> result = memberRoleService.selectByCondition(condition);
        Page wrapped = new MemberRoleWrapper(result).wrap();
        return LayuiPageFactory.createPageInfo(wrapped);
    }

    /**
     * 编辑用户角色关系
     */
    @RequestMapping("/edit")
    @BussinessLog(value = "编辑参数", key = "memberRoleId", dict = MemberRoleMap.class)
    @ResponseBody
    public ResponseData edit(MemberRole memberRole) {
        memberRoleService.updateById(memberRole);
        return SUCCESS_TIP;
    }

    /**
     * 添加用户角色关系
     */
    @RequestMapping("/add")
    @BussinessLog(value = "添加用户角色关系", key = "name", dict = MemberRoleMap.class)
    @ResponseBody
    public ResponseData add(MemberRole memberRole, BindingResult result) {
        if (result.hasErrors()) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        if (memberRole == null) {
            return ResponseData.error("参数不能为空");
        }
        this.memberRoleService.addMemberRole(memberRole);
        return SUCCESS_TIP;
    }

    /**
     * 删除用户角色关系
     */
    @RequestMapping("/delete")
    @BussinessLog(value = "删除用户角色关系", key = "memberRoleId", dict = MemberRoleMap.class)
    @ResponseBody
    public ResponseData delete(@RequestParam Long memberRoleId) {
        if (ToolUtil.isEmpty(memberRoleId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.memberRoleService.deleteMemberRole(memberRoleId);
        return SUCCESS_TIP;
    }

    /**
     * 查询内容详情
     */
    @RequestMapping("/content/{memberRoleId}")
    @ResponseBody
    public Object content(@PathVariable("memberRoleId") Long id) {
        MemberRole memberRole = memberRoleService.getById(id);
        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(memberRole);
        return super.warpObject(new MemberRoleWrapper(stringObjectMap));
    }
}