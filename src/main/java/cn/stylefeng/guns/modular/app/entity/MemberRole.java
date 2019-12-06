package cn.stylefeng.guns.modular.app.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import cn.stylefeng.guns.modular.base.entity.BaseEntity;
import lombok.experimental.Accessors;

/**
  * <p> 用户角色关系 实体类 </p>
  *
  * @author yaying.liu
  * @Date 2019-12-06 10:18:16
 */

@Data
@TableName("app_member_role")
@Accessors(chain = true)
public class MemberRole extends BaseEntity implements Serializable {

     private static final long serialVersionUID = 1L;

     /**
      * app用户角色表id
      */
     @TableId(value="member_role_id", type= IdType.AUTO)
     private Long memberRoleId;

     /**
      * 用户id
      */
     @TableField("member_id")
     private Long memberId;

     /**
      * 角色id
      */
     @TableField("role_id")
     private Long roleId;

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