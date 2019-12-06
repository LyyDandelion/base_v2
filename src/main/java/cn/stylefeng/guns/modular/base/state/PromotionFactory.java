package cn.stylefeng.guns.modular.base.state;

import cn.stylefeng.guns.modular.base.util.Result;
import cn.stylefeng.guns.modular.bulletin.entity.*;
import cn.stylefeng.guns.modular.bulletin.mapper.*;
import cn.stylefeng.guns.modular.meta_data.constant.ConfigMap;
import cn.stylefeng.guns.modular.meta_data.entity.Config;
import cn.stylefeng.guns.modular.meta_data.mapper.ConfigMapper;
import cn.stylefeng.guns.modular.promotion.entity.GatewayDefine;
import cn.stylefeng.guns.modular.promotion.entity.GatewayRecord;
import cn.stylefeng.guns.modular.promotion.entity.Wallet;
import cn.stylefeng.guns.modular.promotion.mapper.GatewayDefineMapper;
import cn.stylefeng.guns.modular.promotion.mapper.GatewayRecordMapper;
import cn.stylefeng.guns.modular.promotion.mapper.WalletMapper;
import cn.stylefeng.roses.core.util.SpringContextHolder;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.tools.Tool;
import java.util.Date;
import java.util.List;

@Component
@DependsOn("springContextHolder")
public class PromotionFactory implements IPromotionFactory {


    public static PromotionFactory me() {
        return SpringContextHolder.getBean("promotionFactory");
    }




//    private AppMemberMapper appMemberMapper = SpringContextHolder.getBean(AppMemberMapper.class);



    private ConfigMapper configMapper=SpringContextHolder.getBean(ConfigMapper.class);

    private GatewayRecordMapper gatewayRecordMapper=SpringContextHolder.getBean(GatewayRecordMapper.class);

    private GatewayDefineMapper gatewayDefineMapper=SpringContextHolder.getBean(GatewayDefineMapper.class);


    private WalletMapper walletMapper = SpringContextHolder.getBean(WalletMapper.class);



    /**
     * 获取钱包信息
     *
     * @param memberId
     * @return
     */
    public Wallet getWallet(Long memberId) throws Exception {
        Wallet walletQ = new Wallet();
        walletQ.setMemberId(memberId).setDel("N");
        Wallet walletR = walletMapper.selectOne(new QueryWrapper<>(walletQ));
        if (walletR == null) {
            throw new Exception("错误！钱包为空");
        }
        return walletR;
    }

    /**
     * 网关记录
     */
    public void gatewayRecord(GatewayRecord gatewayRecord){
        gatewayRecord.setCreateUser(-1L);
        gatewayRecordMapper.insert(gatewayRecord);
    }



    /**
     * 获取系统参数配置信息
     * @param code
     * @return
     */
    public String getSysConfigValueByCode(String code)
    {
        Config config=new Config();
        config.setCode(code);

        Config result=configMapper.selectOne(new QueryWrapper<>(config));
        if(result!=null)
        {
            return result.getValue();
        }
        return "";
    }


    /**
     * 根据id获取用户信息
     *
     * @param memberId
     * @return
     */
//    public AppMember getMemberById(Long memberId) {
//        return appMemberMapper.selectById(memberId);
//    }

    /**
     * 根据id获取用户信息
     *
     * @param memberId
     * @return
     */
//    public String getMemberNameById(Long memberId) {
//
//        AppMember appMember = appMemberMapper.selectById(memberId);
//        if (appMember == null) {
//            return "";
//        }
//        return appMember.getName();
//
//    }

    /**
     *  是否开放日志
     * @param apiGatewayCode 网关编码
     * @return
     */
    public boolean isOpenLog(String apiGatewayCode) {
        if(!existGateWay(apiGatewayCode))//网关是否存在
        {
            return false;
        }
        GatewayDefine define=new GatewayDefine();
        define.setDel("N").setCode(apiGatewayCode).setStatus(ProConst.GatewayStatusEnum.OPEN_LOG.getCode());
        if(gatewayDefineMapper.selectOne(new QueryWrapper<>(define))==null){
            return false;
        }
        return true;
    }



    /**
     * 是否打开网关
     * @param apiGatewayCode
     * @return
     */
    public boolean isEnableGateway(String apiGatewayCode) {
        if(!existGateWay(apiGatewayCode))
        {
            return false;
        }
        GatewayDefine define=new GatewayDefine();
        define.setDel("N").setCode(apiGatewayCode);
        if(gatewayDefineMapper.selectOne(new QueryWrapper<>(define).in("status",ProConst.GatewayStatusEnum.OPEN.getCode(),ProConst.GatewayStatusEnum.OPEN_LOG.getCode()))==null){
            return false;
        }
        return true;
    }

    /**
     * 网关是否存在
     * @param apiGatewayCode
     * @return
     */
    public boolean existGateWay(String apiGatewayCode) {
        GatewayDefine define=new GatewayDefine();
        define.setDel("N").setCode(apiGatewayCode);
        if(gatewayDefineMapper.selectList(new QueryWrapper<>(define)).size()>0) {
            return true;
        }
        return false;
    }
}
