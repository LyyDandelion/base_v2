package cn.stylefeng.guns.modular.app.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.app.common.ApiStatus;
import cn.stylefeng.guns.modular.app.common.StatusCode;
import cn.stylefeng.guns.modular.app.dto.ApiRegionDto;
import cn.stylefeng.guns.modular.app.entity.Member;
import cn.stylefeng.guns.modular.app.vo.LoginVo;
import cn.stylefeng.guns.modular.app.vo.RegVo;
import cn.stylefeng.guns.modular.app.vo.VersionVo;
import cn.stylefeng.guns.modular.base.state.Constant;
import cn.stylefeng.guns.modular.base.state.ProConst;
import cn.stylefeng.guns.modular.base.state.PromotionFactory;
import cn.stylefeng.guns.modular.base.util.RedisUtil;
import cn.stylefeng.guns.modular.base.util.Result;
import cn.stylefeng.guns.modular.bulletin.entity.AppVersion;
import cn.stylefeng.guns.modular.bulletin.service.AppVersionService;
import cn.stylefeng.guns.modular.bulletin.service.SendSMSExtService;
import cn.stylefeng.guns.modular.com.entity.Region;
import cn.stylefeng.guns.modular.com.service.RegionService;
import cn.stylefeng.guns.modular.promotion.entity.Declares;
import cn.stylefeng.guns.modular.promotion.entity.Wallet;
import cn.stylefeng.guns.modular.promotion.service.CashflowService;
import cn.stylefeng.guns.modular.promotion.service.DeclaresService;
import cn.stylefeng.guns.modular.promotion.service.WalletService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

@Service
public class HomeService {

    @Autowired
    MemberService memberService;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    DeclaresService declaresService;

    @Autowired
    AppVersionService versionService;

    @Autowired
    WalletService walletService;


    @Autowired
    CashflowService cashflowService;

    @Autowired
    RegionService regionService;

    @Autowired
    SendSMSExtService sendSMSExtService;

    /**
     * 注册
     *
     * @param regVo
     * @return
     */
    public Result register(RegVo regVo) {

        if (!StrUtil.equals(regVo.getPassword(), regVo.getConfirmPwd())) {
            return Result.fail(HttpStatus.BAD_REQUEST.value(), "两次输入密码不同");
        }
        //用户名是否存在
        Member member = new Member();
        member.setPhone(regVo.getPhone());
        if (memberService.getOne(new QueryWrapper<>(member)) != null) {
            return Result.fail(StatusCode.VERIFY, "用户已存在");
        }

        String msg = (String) redisUtil.get(Constant.SMS + regVo.getPhone());
        if (!StrUtil.equals(msg, regVo.getMsg())) {
            return Result.fail(ApiStatus.MSG_ERROR.code(), ApiStatus.MSG_ERROR.msg());
        }

        //直推人
        Member director = PromotionFactory.me().getMember(null, regVo.getInviteCode());
        if (director == null) {
            return Result.fail(ApiStatus.NOT_DIRECTOR.code(), ApiStatus.NOT_DIRECTOR.msg());
        }

        BeanUtil.copyProperties(member, regVo);
        // 完善账号信息
        String salt = ShiroKit.getRandomSalt(5);
        String password = ShiroKit.md5(regVo.getPassword(), salt);

        boolean flag = true;
        String uid = RandomUtil.randomNumbers(8);
        while (flag) {
            Member uidM = new Member();
            uidM.setUid(uid);
            if (memberService.getOne(new QueryWrapper<>(uidM)) != null) {
                uid = RandomUtil.randomNumbers(8);
            } else {
                flag = false;
            }
        }
        StringBuffer pids = new StringBuffer();
        pids.append("/").append(director.getMemberId()).append(director.getParentRefereeId());
        member.setPassword(password)
                .setSalt(salt)
                .setRefereeId(director.getMemberId())
                .setParentRefereeId(pids.toString())
                .setType(ProConst.MemberType.TEMP.code())
                .setUid(uid)
                .setRegisterTime(new Date())
                .setCreateUser(-1L)
        ;
        //删除验证码
        redisUtil.del(Constant.SMS + regVo.getPhone());
        return memberService.save(member) ? Result.success("注册成功") : Result.fail(StatusCode.FAIL, "注册失败");
    }


    @Transactional(rollbackFor = Exception.class)
    public Result login(LoginVo loginVo) throws Exception {

        //先判断账号是否存在
        Member accountQ = new Member();
        accountQ.setPhone(loginVo.getPhone())
                .setDel("N")
                .setStatus("Y");

        Member resultMember = memberService.getOne(new QueryWrapper(accountQ));
        String token = Constant.TOKEN + IdUtil.simpleUUID();

        if (resultMember == null) {
            return Result.fail(StatusCode.NOT_FOUND, "账号不存在");
        }

        //验证密码
        String salt = resultMember.getSalt();

        String checkPwd = ShiroKit.md5(loginVo.getPassword(), salt);
        if (!StrUtil.equals(checkPwd, resultMember.getPassword())) {
            return Result.fail(StatusCode.VERIFY, "用户名或密码错误");
        }


        boolean packet = false;
        String money = "0";


        /**
         * 登录时校验用户是否有钱包，没有则创建一个钱包
         */
        Wallet walletQ = new Wallet();
        walletQ.setMemberId(resultMember.getMemberId()).setDel("N");

        Wallet walletR = walletService.getOne(new QueryWrapper<>(walletQ));

        if (walletR == null) {
            walletQ.setCreateUser(resultMember.getMemberId());
            walletService.save(walletQ);
        }


        Wallet wallet = PromotionFactory.me().getWallet(resultMember.getMemberId());

        BigDecimal packetMoney = new BigDecimal(money);


        resultMember.setLastLogin(new Date()).setUpdateUser(resultMember.getMemberId());
        memberService.updateById(resultMember);

        redisUtil.set(token, resultMember, Long.parseLong(PromotionFactory.me().getSysConfigValueByCode(Constant.TOKEN_EXPIRE)));

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("packet", packet);
        map.put("price", money);

        return Result.success("登陆结果", map);
    }


    public Result forgetPwd(RegVo forgetVo) {

        Member queryMember = new Member();
        queryMember.setPhone(forgetVo.getPhone())
                .setStatus("Y")
                .setDel("N");

        Member resultMember = memberService.getOne(new QueryWrapper<>(queryMember));
        if (resultMember == null) {
            return Result.fail(StatusCode.NOT_FOUND, "用户名或安全密码有误");
        }
        if (!StrUtil.equals(forgetVo.getPassword(), forgetVo.getConfirmPwd())) {
            return Result.fail(HttpStatus.BAD_REQUEST.value(), "两次输入密码不同");
        }

        // 完善账号信息
        String salt = ShiroKit.getRandomSalt(5);
        String password = ShiroKit.md5(forgetVo.getPassword(), salt);

        resultMember.setSalt(salt)
                .setPassword(password)
                .setUpdateUser(-1L);
        return memberService.updateById(resultMember) ? Result.success("修改成功") : Result.fail(StatusCode.FAIL, "修改失败");
    }


    /**
     * 用户协议
     *
     * @return
     */
    public Result declares() {
        List<Declares> list = declaresService.list();
        if (list.size() > 0) {
            return Result.success("用户协议", list.get(0).getContent());
        }
        return Result.success("暂无用户协议，请联系管理员");
    }

    public Result version(@Valid VersionVo versionVo) {

        Map<String, Object> map = new HashMap<>();
        AppVersion query = new AppVersion();
        query.setType(versionVo.getType());
        AppVersion versionR = versionService.getOne(new QueryWrapper<>(query));
        if (versionR == null) {
            return Result.fail(StatusCode.NOT_FOUND, "版本信息为空，联系管理员");
        }
        if (versionR.getVersion().equals(versionVo.getVersion())) {
            map.put("update", "N");
        } else {
            map.put("update", "Y");
        }
        map.put("content", versionR.getContent());
        map.put("address", versionR.getAddress());
        map.put("version", versionR.getVersion());
        return Result.success("返回结果", map);
    }

    public Result logout(String token) {
        redisUtil.del(token);
        return Result.success("已退出");
    }

    public Result areaList(int levelType, int regionId) {
        Region region = new Region();
        region.setLevelType(levelType).setParentId(regionId);
        List list = regionService.getBaseMapper().dtoList(region);
        return Result.success(list);
    }


    /**
     * 获取手机验证码
     *
     * @param phone
     * @return
     */
    public Result getMsg(String token, String phone, Long type) {

        if (StrUtil.equals(token, "-1") && StrUtil.equals(phone, "1")) {
            return Result.fail(ApiStatus.BAD_REQUEST.code(), ApiStatus.BAD_REQUEST.msg());
        }
        //查手机号
        if (StrUtil.equals(token, "-1")) {
            if (!phone.matches("^1\\d{10}$")) {
                return Result.fail(StatusCode.FAIL, "电话不合法");
            }
        } else {//查token
            Member member = (Member) redisUtil.get(token);
            phone = member.getPhone();
        }

        if (StringUtils.isNotBlank(phone)) {
            int number = (int) ((Math.random() * 9 + 1) * 100000);
//            String content = "您的验证码为：%s,该验证码5分钟内有效，请勿泄露于他人。【168】";
            String content = PromotionFactory.me().getSysConfigValueByCode(Constant.SMS_CONTENT);
            content = String.format(content, number);
            /**
             *  短信开关
             *
             */

            if (PromotionFactory.me().getSysConfigValueByCode(Constant.SMS_OPEN).equals("Y")) {
                if (sendSMSExtService.sendSms(content, phone, type)) {
                    redisUtil.set(Constant.SMS + phone, String.valueOf(number), Constant.SMS_TIMEOUT);
                    return Result.success("发送成功", 200, null);
                }
            } else {
                redisUtil.set(Constant.SMS + phone, String.valueOf(123456), Constant.SMS_TIMEOUT);
                Map<String, Object> map = new HashMap<>();
                map.put("msg", 123456);
//                map.put("content", content);
                return Result.success("本地测试，发送成功,", 200, map);
            }


            return Result.fail(StatusCode.FAIL, "发送失败，请重新发送");
        }
        return Result.fail(StatusCode.FAIL, "发送失败，手机号码有误");
    }
}
