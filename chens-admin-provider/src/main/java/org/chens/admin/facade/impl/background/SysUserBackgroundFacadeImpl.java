package org.chens.admin.facade.impl.background;

import org.chens.admin.entity.SysRole;
import org.chens.admin.entity.SysUserRole;
import org.chens.admin.param.Role;
import org.chens.admin.param.RolesInUserVo;
import org.chens.admin.service.ISysRoleService;
import org.chens.admin.service.ISysUserRoleService;
import org.chens.admin.service.background.SysUserBackgroundFacade;
import org.chens.core.exception.BaseExceptionEnum;
import org.chens.core.util.StringUtils;
import org.chens.core.vo.Result;
import org.chens.framework.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author songchunlei
 * @since 2018/9/29
 */
public class SysUserBackgroundFacadeImpl implements SysUserBackgroundFacade {

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @Autowired
    private ISysRoleService roleService;


    @Override
    public Result<List<Role>> getRoleListByUserId(String userId) {
        List<SysRole> sysRoleList = roleService.getRoleListByUserId(userId);
        return Result.getSuccess(BeanUtil.do2bo4List(sysRoleList,Role.class));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Boolean> addRolesInUser(RolesInUserVo rolesInUserVo) {
        List<String> sysRoles = rolesInUserVo.getSysRoles();
        String userId = rolesInUserVo.getUserId();
        if (CollectionUtils.isEmpty(sysRoles) || StringUtils.isEmpty(userId)) {
            return Result.getError(BaseExceptionEnum.REQUEST_NULL);
        }
        List<SysUserRole> sysUserRoles = new ArrayList<>();
        for (String s : sysRoles) {
            if (StringUtils.isNotEmpty(s)) {
                sysUserRoles.add(new SysUserRole(rolesInUserVo.getUserId(), s));
            }
        }
        return Result.getSuccess(sysUserRoleService.insertBatch(sysUserRoles));
    }
}
