package org.chens.admin.service;

import org.chens.admin.param.Menu;
import org.chens.admin.param.MenuTree;
import org.chens.core.vo.Result;

import java.util.List;

/**
 * 系统菜单服务
 *
 * @author songchunlei
 * @since 2018/9/29
 */
public interface SysMenuFacade {

    /**
     * 获取全量菜单
     * 
     * @return
     */
    Result<List<MenuTree>>  getAllMenuTreeList();
}
