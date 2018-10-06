package org.chens.admin.service.background;

import org.chens.admin.param.RestPwd;
import org.chens.admin.param.Role;
import org.chens.admin.param.RolesInUserVo;
import org.chens.admin.param.User;
import org.chens.core.vo.PageResult;
import org.chens.core.vo.QueryPageEntity;
import org.chens.core.vo.Result;

import java.util.List;

/**
 * 用户后台管理服务
 *
 * @author songchunlei
 * @since 2018/9/29
 */
public interface SysUserBackgroundFacade {

    /**
     * 根据用户id获取角色列表
     *
     * @param userId
     * @return
     */
    Result<List<Role>> getRoleListByUserId(String userId);


    /**
     * 保存用户-角色关系
     * @param rolesInUserVo
     * @return
     */
    Result<Boolean> addRolesInUser(RolesInUserVo rolesInUserVo);

    /**
     * 保存用户
     * @param user
     * @return
     */
    Result<Boolean> insert(User user);

    /**
     * 更新用户
     * @param user
     * @return
     */
    Result<Boolean> updateById(User user);

    /**
     * 重置密码
     * @param restPwd
     * @return
     */
    Result<String> restPwd(RestPwd restPwd);

    /**
     * 根据角色id获取用户列表
     * @param page
     * @return
     */
    Result<PageResult<User>> getUserListByRoleId(QueryPageEntity<User> page);

    /**
     * 根据当前租户id获取用户列表
     * @param page
     * @param tenantId
     * @return
     */
    Result<PageResult<User>> getUserListByTenantId(QueryPageEntity<User> page,String tenantId);
}
