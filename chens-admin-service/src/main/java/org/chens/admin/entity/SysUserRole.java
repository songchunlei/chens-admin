package org.chens.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.chens.app.vo.BaseEntity;

/**
 * <p>
 * 用户-角色
 * </p>
 *
 * @author chunlei.song@live.com
 * @since 2018-03-04
 */
@Data
@TableName("sys_user_role")
public class SysUserRole extends BaseEntity<SysUserRole> {

    private static final long serialVersionUID = 1L;

    @TableField("user_id")
    private String userId;

    @TableField("role_id")
    private String roleId;

    /**
     * 用户串，逗号隔开
     */
    @TableField(exist = false)
    private String users;

    /**
     * 角色串，逗号隔开
     */
    @TableField(exist = false)
    private String roles;

    public SysUserRole() {
    }

    public SysUserRole(String userId, String roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

}
