package org.chens.admin.facade.impl.background;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.chens.admin.entity.SysRoleMenu;
import org.chens.admin.entity.SysUserRole;
import org.chens.admin.param.RolesInUserVo;
import org.chens.admin.param.UsersInRoleVo;
import org.chens.admin.service.ISysRoleMenuService;
import org.chens.admin.service.ISysRoleService;
import org.chens.admin.service.ISysUserRoleService;
import org.chens.admin.service.background.SysRoleBackgroundFacade;
import org.chens.core.exception.BaseExceptionEnum;
import org.chens.core.util.StringUtils;
import org.chens.core.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色管理服务
 *
 * @author songchunlei
 * @since 2018/9/30
 */
public class SysRoleBackgroundFacadeImpl implements SysRoleBackgroundFacade {

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @Autowired
    private ISysRoleMenuService sysRoleMenuService;

    @Autowired
    private ISysRoleService sysRoleService;

    @Override
    public Result<Boolean> deleteById(String id) {
        // 1.删除用户-关联关系
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setRoleId(id);
        sysUserRoleService.delete(new EntityWrapper<>(sysUserRole));
        // 2.删除角色-菜单关系
        SysRoleMenu sysRoleMenu = new SysRoleMenu();
        sysRoleMenu.setRoleId(id);
        sysRoleMenuService.delete(new EntityWrapper<>(sysRoleMenu));
        // 3.删除角色
        return Result.getSuccess(sysRoleService.deleteById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Boolean> addUsersInRole(UsersInRoleVo usersInRoleVo) {
        List<String> users = usersInRoleVo.getUsers();
        String roleId = usersInRoleVo.getRoleId();
        if (CollectionUtils.isEmpty(users) || StringUtils.isEmpty(roleId)) {
            return Result.getError(BaseExceptionEnum.REQUEST_NULL);
        }
        List<SysUserRole> sysUserRoles = new ArrayList<>();
        for (String user : users) {
            SysUserRole r = new SysUserRole(user, roleId);
            sysUserRoles.add(r);
        }
        // 将选中角色id进行保存处理
        return Result.getSuccess(sysUserRoleService.insertBatch(sysUserRoles));

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Boolean> deleteUsersInRole(UsersInRoleVo usersInRoleVo) {
        List<String> users = usersInRoleVo.getUsers();
        String roleId = usersInRoleVo.getRoleId();
        if (CollectionUtils.isEmpty(users) || StringUtils.isEmpty(roleId)) {
            return Result.getError(BaseExceptionEnum.REQUEST_NULL);
        }
        for (String user : users) {
            SysUserRole r = new SysUserRole(user, roleId);
            sysUserRoleService.delete(new EntityWrapper<>(r));
        }
        return Result.getSuccess(true);
    }
}
