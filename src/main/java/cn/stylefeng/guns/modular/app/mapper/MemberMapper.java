package cn.stylefeng.guns.modular.app.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import cn.stylefeng.guns.modular.app.entity.Member;
/**
 * 用户信息Mapper 接口
 *
 * @author yaying.liu
 * @Date 2019-12-06 09:50:49
 */
public interface MemberMapper extends BaseMapper<Member> {

    Page<Map<String,Object>> selectByCondition(@Param("page") Page page, @Param("condition") String condition);

}