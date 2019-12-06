package cn.stylefeng.guns.modular.app.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import cn.stylefeng.guns.modular.app.entity.MemberRole;
/**
 * 用户角色关系Mapper 接口
 *
 * @author yaying.liu
 * @Date 2019-12-06 10:18:16
 */
public interface MemberRoleMapper extends BaseMapper<MemberRole> {

    Page<Map<String,Object>> selectByCondition(@Param("page") Page page, @Param("condition") String condition);

}