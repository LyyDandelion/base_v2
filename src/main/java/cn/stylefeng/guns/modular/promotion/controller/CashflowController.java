package cn.stylefeng.guns.modular.promotion.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.promotion.constant.CashflowMap;
import cn.stylefeng.guns.modular.promotion.entity.Cashflow;
import cn.stylefeng.guns.modular.promotion.service.CashflowService;
import cn.stylefeng.guns.modular.promotion.wrapper.CashflowWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 现金流控制器
 *
 * @author yaying.liu
 * @Date 2019-10-15 11:12:37
 */
@Controller
@RequestMapping("/cashflow")
public class CashflowController extends BaseController {

    private String PREFIX = "/modular/promotion/cashflow/";

     @Autowired
     private CashflowService cashflowService;

    /**
     * 跳转到现金流首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "cashflow.html";
    }

    /**
     * 跳转到添加现金流
     */
    @RequestMapping("/cashflow_add")
    public String cashflowAdd() {
        return PREFIX + "cashflow_add.html";
    }

    /**
     * 跳转到修改现金流
     */
    @RequestMapping("/cashflow_edit")
    public String cashflowEdit(Long cashflowId, Model model) {
        Cashflow condition = this.cashflowService.getById(cashflowId);
        model.addAllAttributes(BeanUtil.beanToMap(condition));
        LogObjectHolder.me().set(condition);
        return PREFIX + "cashflow_edit.html";
    }

    /**
     * 现金流详情
     */
    @RequestMapping(value = "/detail/{cashflowId}")
    @ResponseBody
    public Object detail(@PathVariable("cashflowId") Long cashflowId) {
        Cashflow entity = cashflowService.getById(cashflowId);
      //  CashflowDto conditionDto = new CashflowDto();
      //  BeanUtil.copyProperties(entity, conditionDto);
      //  return conditionDto;
        return entity;
    }


    /**
     * 查询现金流列表
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String condition) {
        //根据条件查询
        Page<Map<String, Object>> result = cashflowService.selectByCondition(condition);
        Page wrapped = new CashflowWrapper(result).wrap();
        return LayuiPageFactory.createPageInfo(wrapped);
    }

    /**
     * 编辑现金流
     */
    @RequestMapping("/edit")
    @BussinessLog(value = "编辑参数", key = "cashflowId", dict = CashflowMap.class)
    @ResponseBody
    public ResponseData edit(Cashflow cashflow) {
        cashflowService.updateById(cashflow);
        return SUCCESS_TIP;
    }

    /**
     * 添加现金流
     */
    @RequestMapping("/add")
    @BussinessLog(value = "添加现金流", key = "name", dict = CashflowMap.class)
    @ResponseBody
    public ResponseData add(Cashflow cashflow, BindingResult result) {
        if (result.hasErrors()) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        if (cashflow == null) {
            return ResponseData.error("参数不能为空");
        }
        this.cashflowService.addCashflow(cashflow);
        return SUCCESS_TIP;
    }

    /**
     * 删除现金流
     */
    @RequestMapping("/delete")
    @BussinessLog(value = "删除现金流", key = "cashflowId", dict = CashflowMap.class)
    @ResponseBody
    public ResponseData delete(@RequestParam Long cashflowId) {
        if (ToolUtil.isEmpty(cashflowId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.cashflowService.deleteCashflow(cashflowId);
        return SUCCESS_TIP;
    }

    /**
     * 查询内容详情
     */
    @RequestMapping("/content/{cashflowId}")
    @ResponseBody
    public Object content(@PathVariable("cashflowId") Long id) {
        Cashflow cashflow = cashflowService.getById(id);
        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(cashflow);
        return super.warpObject(new CashflowWrapper(stringObjectMap));
    }
}