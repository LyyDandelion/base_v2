package cn.stylefeng.guns.modular.app.service;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.Map;
import cn.stylefeng.guns.modular.app.entity.MemberRole;
import cn.stylefeng.guns.modular.app.mapper.MemberRoleMapper;
/**
 * 用户角色关系Service
 *
 * @author yaying.liu
 * @Date 2019-12-06 10:18:16
 */
@Service
public  class MemberRoleService extends ServiceImpl<MemberRoleMapper,MemberRole>{

    /**
    * 查询用户角色关系
    */
    public Page<Map<String,Object>> selectByCondition(String condition) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.selectByCondition(page,condition);
    }

    /**
    * 删除用户角色关系
    */
    public void deleteMemberRole(Long memberRoleId) {
        MemberRole entity=this.baseMapper.selectById(memberRoleId);
        //将删除标志设置为Y，表示删除
        entity.setDel("Y");
        this.baseMapper.updateById(entity);
    }

    /**
    * 添加用户角色关系
    */
    public void addMemberRole(MemberRole memberRole) {
        this.baseMapper.insert(memberRole);
    }
}