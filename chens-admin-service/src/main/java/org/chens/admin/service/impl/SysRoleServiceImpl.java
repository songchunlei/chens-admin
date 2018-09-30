package org.chens.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.chens.admin.entity.SysRoleMenu;
import org.chens.admin.service.ISysRoleMenuService;
import org.chens.admin.service.ISysUserRoleService;
import org.chens.admin.entity.SysRole;
import org.chens.admin.mapper.SysRoleMapper;
import org.chens.admin.service.ISysRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.chens.admin.entity.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-04
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Override
    public List<SysRole> getRoleListByUserId(String userId) {
        return baseMapper.getRoleListByUserId(userId);
    }
}
