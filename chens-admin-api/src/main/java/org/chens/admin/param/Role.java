package org.chens.admin.param;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色
 *
 * @author songchunlei
 * @since 2018/9/30
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 6892159117500845595L;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色编码
     */
    private String roleCode;
    /**
     * 是否删除
     */
    private String isDelete;
    /**
     * 角色下的菜单
     */
    private List<Menu> sysMenus;
}
