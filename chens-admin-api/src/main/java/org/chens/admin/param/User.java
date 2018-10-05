package org.chens.admin.param;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户
 *
 * @author songchunlei
 * @since 2018/10/5
 */
@Data
public class User implements Serializable{

    /**
     * 用户id
     */
    private String id;
    /**
     * 用户姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 账号
     */
    private String username;

    /**
     * 手机
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 租户id
     */
    private String TenantId;

    /**
     * 系统角色id
     */
    private List<String> roles;

    /**
     * 角色id（用于查询角色下用户）
     */
    private String roleId;
}
