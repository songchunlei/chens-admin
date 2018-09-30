package org.chens.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import org.chens.app.annotation.InsertValid;
import org.chens.app.annotation.UpdateValid;
import org.chens.app.vo.BaseEntity;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 租户
 * </p>
 *
 * @author chunlei.song@live.com
 * @since 2018-03-04
 */
@TableName("sys_tenant")
public class SysTenant extends BaseEntity<SysTenant> {

    private static final long serialVersionUID = 1L;

    /**
     * 租户名称
     */
    @NotNull(message = "{tenant.name.null}", groups = { InsertValid.class, UpdateValid.class })
    @TableField("tenant_name")
    private String tenantName;

    /**
     * 租户编码
     */
    @TableField("tenant_code")
    private String tenantCode;

    /**
     * 营业执照
     */
    @NotNull(message = "{tenant.jregLicens.null}", groups = { InsertValid.class, UpdateValid.class })
    @TableField("jreg_licens")
    private String jregLicens;

    /**
     * 代理人账号
     */
    @NotNull(message = "{tenant.userName.null}", groups = { InsertValid.class })
    @TableField("user_name")
    private String userName;

    /**
     * 租户描述
     */
    @TableField("tenant_desc")
    private String tenantDesc;

    /**
     * 租户状态
     */
    private String status;
}
