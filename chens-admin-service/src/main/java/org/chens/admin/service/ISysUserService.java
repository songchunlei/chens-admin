package org.chens.admin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import org.chens.admin.entity.SysUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-04
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 根据角色id获取角色下的用户
     * @param page
     * @param user
     * @return
     */
    Page<SysUser> getUserListByRoleId(Page<SysUser> page, SysUser user);
}
