package cn.stylefeng.guns.modular.promotion.entity;

import cn.stylefeng.guns.modular.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
  * <p> 用户钱包 实体类 </p>
  *
  * @author yaying.liu
  * @Date 2019-10-14 18:05:20
 */

@Data
@TableName("fin_wallet")
@Accessors(chain = true)
public class Wallet extends BaseEntity implements Serializable {

     private static final long serialVersionUID = 1L;

     /**
      * 
      */
     @TableId(value="wallet_id", type= IdType.AUTO)
     private Long walletId;

     /**
      * 
      */
     @TableField("member_id")
     private Long memberId;

     /**
      * 钱包总额
      */
     @TableField("total_price")
     private java.math.BigDecimal totalPrice;

     /**
      * 平台返还总额
      */
     @TableField("return_price")
     private java.math.BigDecimal returnPrice;

     /**
      * 消费券总额
      */
     @TableField("ticket_price")
     private java.math.BigDecimal ticketPrice;

     /**
      * 状态(字典)
      */
     @TableField("status")
     private String status;

     /**
      * 删除标志
      */
     @TableField("del")
     private String del;

     /**
      * 备注
      */
     @TableField("remark")
     private String remark;




 }