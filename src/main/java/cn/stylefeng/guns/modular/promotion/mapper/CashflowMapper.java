package cn.stylefeng.guns.modular.promotion.mapper;

import cn.stylefeng.guns.modular.promotion.entity.Cashflow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 现金流Mapper 接口
 *
 * @author yaying.liu
 * @Date 2019-10-15 11:12:37
 */
public interface CashflowMapper extends BaseMapper<Cashflow> {

    Page<Map<String,Object>> selectByCondition(@Param("page") Page page, @Param("condition") String condition);

}