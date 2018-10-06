package org.chens.admin.param;

import lombok.Data;

import java.io.Serializable;

/**
 * 注册租户
 *
 * @author songchunlei@qq.com
 * @since 2018/3/17
 */
@Data
public class RegisterTenant implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 租户名称
     */
    private String tenantName;

    private String tenantDesc;

    /**
     * 营业执照
     */
    private String jregLicens;

    /**
     * 代理人账户（一般是手机或者邮箱）
     */
    private String userName;

    /**
     * 代理人姓名
     */
    private String user;

    /**
     * 代码人密码
     */
    private String password;

    /**
     * 代理人手机
     */
    private String phone;

    /**
     * 代理人邮箱
     */
    private String email;

}
