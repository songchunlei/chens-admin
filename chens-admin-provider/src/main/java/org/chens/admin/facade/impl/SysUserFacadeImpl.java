package org.chens.admin.facade.impl;

import org.chens.admin.entity.SysMenu;
import org.chens.admin.entity.SysRole;
import org.chens.admin.param.Menu;
import org.chens.admin.param.MenuTree;
import org.chens.admin.param.Role;
import org.chens.admin.service.ISysMenuService;
import org.chens.admin.service.ISysRoleService;
import org.chens.admin.service.SysUserFacade;
import org.chens.admin.util.MenuConvertHelper;
import org.chens.core.tree.BaseTree;
import org.chens.core.tree.TreeUtil;
import org.chens.core.vo.Result;
import org.chens.framework.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 系统用户服务实现
 *
 * @author songchunlei
 * @since 2018/9/30
 */
public class SysUserFacadeImpl implements SysUserFacade{

    @Autowired
    private ISysMenuService sysMenuService;

    @Override
    public Result<List<Menu>> getMenuListByUserId(String userId) {
        //获取系统树列表
        List<SysMenu> sysMenuList = this.getMenuListByUserIdFromDb(userId);
        //获取树列表
        List<Menu> menuList = MenuConvertHelper.convertSysMenuListToMenuList(sysMenuList);
        return Result.getSuccess(menuList);
    }

    @Override
    public Result<List<MenuTree>> getMenuTreeListByUserId(String userId) {
        //获取系统树列表
        List<SysMenu> menuList = this.getMenuListByUserIdFromDb(userId);
        //获取树列表
        List<MenuTree> menuTreeList = MenuConvertHelper.convertSysMenuListToMenuTreeList(menuList);
        //构建树结构
        List<MenuTree> trees = TreeUtil.build(menuTreeList, BaseTree.BASE_TREE_ROOT);
        return Result.getSuccess(trees);
    }


    /**
     * 从数据库根据用户id抽取菜单
     * @param userId
     * @return
     */
    private List<SysMenu> getMenuListByUserIdFromDb(String userId){
        List<SysMenu> sysMenuList =  sysMenuService.getMenuListByUserId(userId);
        return sysMenuList;
    }
}
