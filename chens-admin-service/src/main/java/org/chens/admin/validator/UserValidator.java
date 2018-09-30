package org.chens.admin.validator;

import org.chens.admin.entity.SysUser;
import org.chens.admin.service.ISysUserService;
import org.chens.app.validator.BaseValidator;
import org.chens.framework.util.ApplicationContextUtil;

/**
 * 用户自定义校验
 *
 * @author songchunlei@qq.com
 * @since 2018/3/29
 */
public class UserValidator extends BaseValidator<ISysUserService, SysUser> {

    public UserValidator() {
        // 自定义初始化
        if (service == null) {
            service = ApplicationContextUtil.getBeanByClass(ISysUserService.class);
        }
    }

    public boolean check(SysUser sysUser) throws Exception {
        SysUser query = new SysUser();
        query.setUsername(sysUser.getUsername());
        query.setId(sysUser.getId());
        return this.checkIsNotExist(query);
    }
}
