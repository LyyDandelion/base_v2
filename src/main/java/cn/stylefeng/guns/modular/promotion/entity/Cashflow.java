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
  * <p> 现金流 实体类 </p>
  *
  * @author yaying.liu
  * @Date 2019-10-15 11:12:37
 */

@Data
@TableName("fin_cashflow")
@Accessors(chain = true)
public class Cashflow extends BaseEntity implements Serializable {

     private static final long serialVersionUID = 1L;

     /**
      * 流水id
      */
     @TableId(value="cashflow_id", type= IdType.AUTO)
     private Long cashflowId;

     /**
      * 用户id
      */
     @TableField("member_id")
     private Long memberId;

     /**
      * 流水方向（流入 1 - 流出 - 0）
      */
     @TableField("flow_op")
     private Long flowOp;

     /**
      * 流水类型
      */
     @TableField("flow_type")
     private String flowType;

     /**
      * 流水记录编码
      */
     @TableField("item_code")
     private String itemCode;

     /**
      * 名称
      */
     @TableField("item_name")
     private String itemName;

     /**
      * 流水金额
      */
     @TableField("flow_price")
     private java.math.BigDecimal flowPrice;

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