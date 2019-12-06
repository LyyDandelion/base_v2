package cn.stylefeng.guns.modular.app.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import cn.stylefeng.guns.modular.base.entity.BaseEntity;
import lombok.experimental.Accessors;

/**
  * <p> 用户信息 实体类 </p>
  *
  * @author yaying.liu
  * @Date 2019-12-06 09:50:50
 */

@Data
@TableName("app_member")
@Accessors(chain = true)
public class Member extends BaseEntity implements Serializable {

     private static final long serialVersionUID = 1L;

     /**
      * ID
      */
     @TableId(value="member_id", type= IdType.AUTO)
     private Long memberId;

     /**
      * 手机号码
      */
     @TableField("phone")
     private String phone;

     /**
      * uid
      */
     @TableField("uid")
     private String uid;

     /**
      * 用户类型(0:临时用户 1:会员 2:矿主 3：大矿主 4：超级矿主） 
      */
     @TableField("type")
     private String type;

     /**
      * 密码
      */
     @TableField("password")
     private String password;

     /**
      * 手机号码属于0：国内 1：国外
      */
     @TableField("area")
     private String area;

     /**
      * 密码加盐
      */
     @TableField("salt")
     private String salt;

     /**
      * 交易密码
      */
     @TableField("pay_password")
     private String payPassword;

     /**
      * 交易密码加盐
      */
     @TableField("pay_salt")
     private String paySalt;

     /**
      * 邀请码
      */
     @TableField("invite_code")
     private String inviteCode;

     /**
      * 用户关系链
      */
     @TableField("parent_referee_id")
     private String parentRefereeId;

     /**
      * 推荐人
      */
     @TableField("referee_id")
     private Long refereeId;

     /**
      * 助记词（备用）
      */
     @TableField("mnemonic")
     private String mnemonic;

     /**
      * 登录设备（备用）
      */
     @TableField("login_equipment")
     private String loginEquipment;

     /**
      * 累计收益（备用）
      */
     @TableField("mall_points")
     private java.math.BigDecimal mallPoints;

     /**
      * 算力剩余天数
      */
     @TableField("rest_day")
     private Long restDay;

     /**
      * 系统是否修改过用户类型0：否 1：是
      */
     @TableField("state")
     private String state;

     /**
      * 昵称
      */
     @TableField("nick_name")
     private String nickName;

     /**
      * 是否实名认证0：否 1：是 2 :审核中
      */
     @TableField("real_status")
     private String realStatus;

     /**
      * 区号
      */
     @TableField("area_code")
     private String areaCode;

     /**
      * 登录失败记录
      */
     @TableField("failure_sign")
     private Long failureSign;

     /**
      * 头像
      */
     @TableField("head")
     private String head;

     /**
      * 是否开通积分划转(0:否 1：是）
      */
     @TableField("points")
     private String points;

     /**
      * 真实姓名
      */
     @TableField("real_name")
     private String realName;

     /**
      * 最近一次登录
      */
     @TableField("last_login")
     private java.util.Date lastLogin;

     /**
      * 注册时间
      */
     @TableField("register_time")
     private java.util.Date registerTime;

     /**
      * 直推比例（备用）
      */
     @TableField("direct_rate")
     private String directRate;

     /**
      * 团队比例（备用）
      */
     @TableField("team_rate")
     private String teamRate;

     /**
      * 省
      */
     @TableField("province_id")
     private Long provinceId;

     /**
      * 市
      */
     @TableField("city_id")
     private Long cityId;

     /**
      * 区
      */
     @TableField("area_id")
     private Long areaId;

     /**
      * 是否省代理（0：否 1：是）
      */
     @TableField("province_proxy")
     private String provinceProxy;

     /**
      * 是否市代理（0：否 1：是）
      */
     @TableField("city_proxy")
     private String cityProxy;

     /**
      * 是否区/县代理（0：否 1：是）
      */
     @TableField("area_id_proxy")
     private String areaIdProxy;

     /**
      * 是否理事（0：否 1：是
      */
     @TableField("director")
     private String director;

     /**
      * 是否合创(0:否 1：是）
      */
     @TableField("cooperation")
     private String cooperation;

     /**
      * 版本号
      */
     @TableField("version")
     private Long version;

     /**
      * 状态(字典)(启用-Y：禁用-N)
      */
     @TableField("status")
     private String status;

     /**
      * 删除标志(Y:删除 N:未删除）
      */
     @TableField("del")
     private String del;

     /**
      * 备注
      */
     @TableField("remark")
     private String remark;




 }