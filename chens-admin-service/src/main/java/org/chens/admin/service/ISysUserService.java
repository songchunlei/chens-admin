package org.chens.admin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import org.chens.admin.entity.SysUser;
import org.chens.admin.vo.RestPwd;
import org.chens.framework.exception.BaseException;

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
     * 重置密码
     * @param restPwd 用户id 是否用随机密码串
     * @return
     */
    String restPwd(RestPwd restPwd);

    /**
     * 根据角色id获取角色下的用户
     * @param page
     * @param user
     * @return
     */
    Page<SysUser> getUserListByRoleId(Page<SysUser> page, SysUser user);

    /**
     * 根据租户id获取角色下的用户
     * @param page
     * @param user
     * @return
     */
    Page<SysUser> getUserListByTenantId(Page<SysUser> page, SysUser user);
}
