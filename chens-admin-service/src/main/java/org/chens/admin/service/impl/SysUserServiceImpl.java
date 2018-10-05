package org.chens.admin.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import org.chens.admin.exception.AdminExceptionEnum;
import org.chens.core.exception.BaseException;
import org.chens.core.util.StringUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.chens.admin.mapper.SysUserMapper;
import org.chens.admin.service.ISysUserService;
import org.chens.admin.entity.SysUser;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author chunlei.song@live.com
 * @since 2018-03-04
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public Page<SysUser> getUserListByRoleId(Page<SysUser> page, SysUser user) {
        if (StringUtils.isEmpty(user.getRoleId())) {
            throw new BaseException(AdminExceptionEnum.ROLE_ID_IS_NULL);
        }
        page.setRecords(baseMapper.getUserListByRoleId(page, user));
        return page;
    }
}
