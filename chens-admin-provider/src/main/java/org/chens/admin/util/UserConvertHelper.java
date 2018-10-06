package org.chens.admin.util;

import org.chens.admin.entity.SysUser;
import org.chens.core.vo.UserInfo;

/**
 * 系统用户转换
 *
 * @author songchunlei@qq.com
 * @create 2018/5/14
 */
public class UserConvertHelper {

    /**
     * SysUser --> UserInfo
     * @param sysUser 系统用户
     * @param token
     * @return
     */
    public static UserInfo getUserInfoBySysUser(SysUser sysUser, String token)
    {
        return new UserInfo(sysUser.getId(),sysUser.getName(),sysUser.getUsername(),sysUser.getTenantId(),token);
    }
}
