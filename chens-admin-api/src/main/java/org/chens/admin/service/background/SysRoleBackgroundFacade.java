package org.chens.admin.service.background;

import org.chens.admin.param.RolesInUserVo;
import org.chens.admin.param.UsersInRoleVo;
import org.chens.core.vo.Result;

/**
 * 系统角色管理服务
 *
 * @author songchunlei
 * @since 2018/9/30
 */
public interface SysRoleBackgroundFacade {

    /**
     * 根据角色id删除角色
     * @param id
     * @return
     */
    Result<Boolean> deleteById(String id);

    /**
     * 给角色增加用户
     * @param usersInRoleVo
     * @return
     */
    Result<Boolean> addUsersInRole(UsersInRoleVo usersInRoleVo);

    /**
     * 给角色删除用户
     * @param usersInRoleVo
     * @return
     */
    Result<Boolean> deleteUsersInRole(UsersInRoleVo usersInRoleVo);
}
