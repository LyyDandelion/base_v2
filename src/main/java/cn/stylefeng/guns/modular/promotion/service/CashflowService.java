package cn.stylefeng.guns.modular.promotion.service;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.modular.promotion.entity.Cashflow;
import cn.stylefeng.guns.modular.promotion.mapper.CashflowMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 现金流Service
 *
 * @author yaying.liu
 * @Date 2019-10-15 11:12:37
 */
@Service
public  class CashflowService extends ServiceImpl<CashflowMapper,Cashflow>{

    /**
    * 查询现金流
    */
    public Page<Map<String,Object>> selectByCondition(String condition) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.selectByCondition(page,condition);
    }

    /**
    * 删除现金流
    */
    public void deleteCashflow(Long cashflowId) {
        Cashflow entity=this.baseMapper.selectById(cashflowId);
        //将删除标志设置为Y，表示删除
        entity.setDel("Y");
        this.baseMapper.updateById(entity);
    }

    /**
    * 添加现金流
    */
    public void addCashflow(Cashflow cashflow) {
        this.baseMapper.insert(cashflow);
    }
}