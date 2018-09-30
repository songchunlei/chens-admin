package org.chens.admin.service;

import org.chens.admin.param.Menu;
import org.chens.admin.param.MenuTree;
import org.chens.admin.param.Role;
import org.chens.core.vo.Result;

import java.util.List;

/**
 * 系统用户服务
 *
 * @author songchunlei
 * @since 2018/9/30
 */
public interface SysUserFacade {

    /**
     * 根据用户id获取对应菜单(权限)
     *
     * @param userId
     * @return
     */
    Result<List<Menu>> getMenuListByUserId(String userId);

    /**
     * 根据用户id获取对应菜单(权限)树
     *
     * @param userId
     * @return
     */
    Result<List<MenuTree>> getMenuTreeListByUserId(String userId);

}
