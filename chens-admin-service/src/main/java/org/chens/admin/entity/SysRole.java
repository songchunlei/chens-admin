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
 * 角色
 * </p>
 *
 * @author chunlei.song@live.com
 * @since 2018-03-04
 */
@TableName("sys_role")
@Data
public class SysRole extends BaseEntity<SysRole> {

    private static final long serialVersionUID = -6454171444113337196L;

    @NotNull(message = "{role.name.null}", groups = { InsertValid.class, UpdateValid.class })
    @TableField("role_name")
    private String roleName;

    @NotNull(message = "{role.code.null}", groups = { InsertValid.class, UpdateValid.class })
    @TableField("role_code")
    private String roleCode;

    @TableField(value = "is_delete")
    @TableLogic
    private String isDelete;

}
