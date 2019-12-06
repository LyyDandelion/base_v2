package cn.stylefeng.guns.modular.promotion.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.promotion.constant.WalletMap;
import cn.stylefeng.guns.modular.promotion.entity.Wallet;
import cn.stylefeng.guns.modular.promotion.service.WalletService;
import cn.stylefeng.guns.modular.promotion.wrapper.WalletWrapper;
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
 * 用户钱包控制器
 *
 * @author yaying.liu
 * @Date 2019-10-14 18:05:20
 */
@Controller
@RequestMapping("/wallet")
public class WalletController extends BaseController {

    private String PREFIX = "/modular/promotion/wallet/";

     @Autowired
     private WalletService walletService;

    /**
     * 跳转到用户钱包首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "wallet.html";
    }

    /**
     * 跳转到添加用户钱包
     */
    @RequestMapping("/wallet_add")
    public String walletAdd() {
        return PREFIX + "wallet_add.html";
    }

    /**
     * 跳转到修改用户钱包
     */
    @RequestMapping("/wallet_edit")
    public String walletEdit(Long walletId, Model model) {
        Wallet condition = this.walletService.getById(walletId);
        model.addAllAttributes(BeanUtil.beanToMap(condition));
        LogObjectHolder.me().set(condition);
        return PREFIX + "wallet_edit.html";
    }

    /**
     * 用户钱包详情
     */
    @RequestMapping(value = "/detail/{walletId}")
    @ResponseBody
    public Object detail(@PathVariable("walletId") Long walletId) {
        Wallet entity = walletService.getById(walletId);
      //  WalletDto conditionDto = new WalletDto();
      //  BeanUtil.copyProperties(entity, conditionDto);
      //  return conditionDto;
        return entity;
    }


    /**
     * 查询用户钱包列表
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String condition) {
        //根据条件查询
        Page<Map<String, Object>> result = walletService.selectByCondition(condition);
        Page wrapped = new WalletWrapper(result).wrap();
        return LayuiPageFactory.createPageInfo(wrapped);
    }

    /**
     * 编辑用户钱包
     */
    @RequestMapping("/edit")
    @BussinessLog(value = "编辑参数", key = "walletId", dict = WalletMap.class)
    @ResponseBody
    public ResponseData edit(Wallet wallet) {
        walletService.updateById(wallet);
        return SUCCESS_TIP;
    }

    /**
     * 添加用户钱包
     */
    @RequestMapping("/add")
    @BussinessLog(value = "添加用户钱包", key = "name", dict = WalletMap.class)
    @ResponseBody
    public ResponseData add(Wallet wallet, BindingResult result) {
        if (result.hasErrors()) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        if (wallet == null) {
            return ResponseData.error("参数不能为空");
        }
        this.walletService.addWallet(wallet);
        return SUCCESS_TIP;
    }

    /**
     * 删除用户钱包
     */
    @RequestMapping("/delete")
    @BussinessLog(value = "删除用户钱包", key = "walletId", dict = WalletMap.class)
    @ResponseBody
    public ResponseData delete(@RequestParam Long walletId) {
        if (ToolUtil.isEmpty(walletId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        this.walletService.deleteWallet(walletId);
        return SUCCESS_TIP;
    }

    /**
     * 查询内容详情
     */
    @RequestMapping("/content/{walletId}")
    @ResponseBody
    public Object content(@PathVariable("walletId") Long id) {
        Wallet wallet = walletService.getById(id);
        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(wallet);
        return super.warpObject(new WalletWrapper(stringObjectMap));
    }
}