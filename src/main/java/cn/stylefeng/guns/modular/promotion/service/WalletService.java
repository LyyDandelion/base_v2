package cn.stylefeng.guns.modular.promotion.service;

import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.modular.promotion.entity.Wallet;
import cn.stylefeng.guns.modular.promotion.mapper.WalletMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 用户钱包Service
 *
 * @author yaying.liu
 * @Date 2019-10-14 18:05:20
 */
@Service
public  class WalletService extends ServiceImpl<WalletMapper,Wallet>{

    /**
    * 查询用户钱包
    */
    public Page<Map<String,Object>> selectByCondition(String condition) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.selectByCondition(page,condition);
    }

    /**
    * 删除用户钱包
    */
    public void deleteWallet(Long walletId) {
        Wallet entity=this.baseMapper.selectById(walletId);
        //将删除标志设置为Y，表示删除
        entity.setDel("Y");
        this.baseMapper.updateById(entity);
    }

    /**
    * 添加用户钱包
    */
    public void addWallet(Wallet wallet) {
        this.baseMapper.insert(wallet);
    }
}