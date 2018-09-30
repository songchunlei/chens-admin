package org.chens.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.chens.app.vo.BaseEntity;

/**
 *
 * 角色菜单
 *
 * @author chunlei.song@live.com
 * @since 2018-03-19
 */
@Data
@TableName("sys_role_menu")
public class SysRoleMenu extends BaseEntity<SysRoleMenu> {

    private static final long serialVersionUID = -7974232924294701644L;

    /**
     * 角色id
     */
    @TableField("role_id")
    private String roleId;

    /**
     * 菜单权限id
     */
    @TableField("menu_id")
    private String menuId;

}
