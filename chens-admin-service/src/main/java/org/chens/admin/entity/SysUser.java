package org.chens.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.chens.app.annotation.InsertValid;
import org.chens.app.annotation.UpdateValid;
import org.chens.app.vo.BaseEntity;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-04
 */
@Data
@TableName("sys_user")
public class SysUser extends BaseEntity<SysUser> {

    private static final long serialVersionUID = 6617577547292285890L;

    /**
     * 用户姓名
     */
    @NotNull(message = "{sysuser.name.null}", groups = { InsertValid.class, UpdateValid.class })
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 账号
     */
    @NotNull(message = "{sysuser.username.null}", groups = { InsertValid.class, UpdateValid.class })
    private String username;

    /**
     * 手机
     */
    @NotNull(message = "{sysuser.phone.null}", groups = { InsertValid.class, UpdateValid.class })
    private String phone;

    /**
     * 邮箱
     */
    @NotNull(message = "{sysuser.email.null}", groups = { InsertValid.class, UpdateValid.class })
    private String email;

    /**
     * 系统角色id
     */
    @TableField(exist = false)
    private List<String> roles;

    /**
     * 角色id（用于查询角色下用户）
     */
    @TableField(exist = false)
    private String roleId;

    @TableLogic
    @TableField(value = "is_delete")
    private String isDelete;

}
