package org.chens.admin.facade.impl.background;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import lombok.extern.slf4j.Slf4j;
import org.chens.admin.constants.AdminConstants;
import org.chens.admin.entity.SysRole;
import org.chens.admin.entity.SysUser;
import org.chens.admin.entity.SysUserRole;
import org.chens.admin.exception.AdminExceptionEnum;
import org.chens.admin.param.Role;
import org.chens.admin.param.RolesInUserVo;
import org.chens.admin.param.User;
import org.chens.admin.service.ISysRoleService;
import org.chens.admin.service.ISysUserRoleService;
import org.chens.admin.service.ISysUserService;
import org.chens.admin.service.background.SysUserBackgroundFacade;
import org.chens.admin.param.RestPwd;
import org.chens.app.util.PageHelper;
import org.chens.core.constants.CommonConstants;
import org.chens.core.enums.YesNoEnum;
import org.chens.core.exception.BaseExceptionEnum;
import org.chens.core.util.StringUtils;
import org.chens.core.vo.PageResult;
import org.chens.core.vo.QueryPageEntity;
import org.chens.core.vo.Result;
import org.chens.framework.security.IPasswordCoder;
import org.chens.framework.security.impl.PasswordCoderByBcrypt;
import org.chens.framework.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author songchunlei
 * @since 2018/9/29
 */
@Slf4j
public class SysUserBackgroundFacadeImpl implements SysUserBackgroundFacade {

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysUserService sysUserService;

    private IPasswordCoder passwordCoder = new PasswordCoderByBcrypt();

    @Override
    public Result<List<Role>> getRoleListByUserId(String userId) {
        List<SysRole> sysRoleList = roleService.getRoleListByUserId(userId);
        return Result.getSuccess(BeanUtil.do2bo4List(sysRoleList, Role.class));
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

    /**
     * 重构创建用户方法 增加加密密码算法
     *
     * @param user
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Boolean> insert(User user) {
        // 对密码加密
        String password = user.getPassword();
        if (StringUtils.isEmpty(password)) {
            // 设置默认密码
            password = CommonConstants.DEFAULT_PASSWORD;
        }
        // 拼装SysUser
        SysUser sysUser = BeanUtil.do2bo(user, SysUser.class);
        sysUser.setPassword(passwordCoder.encoder(password));
        sysUser.setIsDelete(YesNoEnum.NO.getCode());
        // 创建用户
        sysUserService.insert(sysUser);
        // 创建角色
        if (StringUtils.isNotEmpty(sysUser.getId())) {
            if (!CollectionUtils.isEmpty(sysUser.getRoles())) {
                addRolesInUser(new RolesInUserVo(user.getId(), sysUser.getRoles(), null));
            } else {
                addRolesInUser(
                        new RolesInUserVo(user.getId(), Arrays.asList(AdminConstants.SYSROLE_COMMON_ROLE), null));
            }
        }

        return Result.getSuccess(true);
    }

    /**
     * 重构用户更新方法 增加保存角色
     *
     * @param user
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Boolean> updateById(User user) {
        if (StringUtils.isEmpty(user.getId())) {
            return Result.getError(BaseExceptionEnum.DATA_REQUEST_ERROR.getCode(), "用户id为空");
        }
        if (user != null && !CollectionUtils.isEmpty(user.getRoles())) {
            // 用户id
            String userId = user.getId();
            // 1.先清空当前角色
            SysUserRole deleteSysUserRole = new SysUserRole();
            deleteSysUserRole.setUserId(userId);
            sysUserRoleService.delete(new EntityWrapper<>(deleteSysUserRole));
            // 2.替换新角色
            addRolesInUser(new RolesInUserVo(userId, user.getRoles(), null));
        }
        // 拼装SysUser
        SysUser sysUser = BeanUtil.do2bo(user, SysUser.class);
        if (StringUtils.isNotEmpty(user.getPassword())) {
            sysUser.setPassword(passwordCoder.encoder(sysUser.getPassword()));
        }
        return Result.getSuccess(sysUserService.updateById(sysUser));
    }

    @Override
    public Result<String> restPwd(RestPwd restPwd) {
        String password = CommonConstants.DEFAULT_PASSWORD;
        if (restPwd.isRandom()) {
            password = StringUtils.getRandomString(null, 16);
        }
        SysUser sysUser = new SysUser();
        sysUser.setPassword(password);
        sysUser.setId(restPwd.getUserId());
        if (sysUserService.updateById(sysUser)) {
            return Result.getSuccess(password);
        }
        return Result.getError(BaseExceptionEnum.NO_UPDATE);
    }

    @Override
    public Result<PageResult<User>> getUserListByRoleId(QueryPageEntity<User> page) {
        if (page == null) {
            return Result.getError(BaseExceptionEnum.REQUEST_NULL);
        }
        if (StringUtils.isEmpty(page.getSearch().getRoleId())) {
            return Result.getError(AdminExceptionEnum.ROLE_ID_IS_NULL);
        }
        PageResult<User> userPage = PageHelper.autoQueryPage(sysUserService, page, User.class, SysUser.class);
        return Result.getSuccess(userPage);
    }

    @Override
    public Result<PageResult<User>> getUserListByTenantId(QueryPageEntity<User> page, String tenantId) {
        if (page == null) {
            return Result.getError(BaseExceptionEnum.REQUEST_NULL);
        }
        if (StringUtils.isEmpty(tenantId)) {
            return Result.getError(AdminExceptionEnum.TENANT_ID_IS_NULL);
        }
        // 设置当前tenantId
        User query = page.getSearch();
        if (query == null) {
            query = new User();
        }
        query.setTenantId(tenantId);
        page.setSearch(query);
        PageResult<User> userPage = PageHelper.autoQueryPage(sysUserService, page, User.class, SysUser.class);
        return Result.getSuccess(userPage);
    }

}
