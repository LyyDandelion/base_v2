package cn.stylefeng.guns.modular.app.service;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.Map;
import cn.stylefeng.guns.modular.app.entity.Member;
import cn.stylefeng.guns.modular.app.mapper.MemberMapper;
/**
 * 用户信息Service
 *
 * @author yaying.liu
 * @Date 2019-12-06 09:50:49
 */
@Service
public  class MemberService extends ServiceImpl<MemberMapper,Member>{

    /**
    * 查询用户信息
    */
    public Page<Map<String,Object>> selectByCondition(String condition) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.selectByCondition(page,condition);
    }

    /**
    * 删除用户信息
    */
    public void deleteMember(Long memberId) {
        Member entity=this.baseMapper.selectById(memberId);
        //将删除标志设置为Y，表示删除
        entity.setDel("Y");
        this.baseMapper.updateById(entity);
    }

    /**
    * 添加用户信息
    */
    public void addMember(Member member) {
        this.baseMapper.insert(member);
    }
}