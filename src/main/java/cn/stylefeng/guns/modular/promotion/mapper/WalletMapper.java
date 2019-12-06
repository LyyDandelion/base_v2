package cn.stylefeng.guns.modular.promotion.mapper;

import cn.stylefeng.guns.modular.promotion.entity.Wallet;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 用户钱包Mapper 接口
 *
 * @author yaying.liu
 * @Date 2019-10-14 18:05:20
 */
public interface WalletMapper extends BaseMapper<Wallet> {

    Page<Map<String,Object>> selectByCondition(@Param("page") Page page, @Param("condition") String condition);

}