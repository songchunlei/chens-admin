package org.chens.admin.service;

import org.chens.admin.entity.SysMenu;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单服务
 * </p>
 *
 * @author chunlei.song@live.com
 * @since 2018-03-09
 */
public interface ISysMenuService extends IService<SysMenu> {
    /**
     * 根据用户id获取菜单
     * 
     * @param userId
     * @return
     */
    List<SysMenu> getMenuListByUserId(String userId);
}
