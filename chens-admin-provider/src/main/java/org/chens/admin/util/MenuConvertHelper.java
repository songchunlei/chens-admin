package org.chens.admin.util;

import com.google.common.collect.Lists;
import org.chens.admin.entity.SysMenu;
import org.chens.admin.param.Menu;
import org.chens.admin.param.MenuTree;
import org.chens.framework.util.BeanUtil;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 菜单转换工具
 *
 * @author songchunlei
 * @since 2018/9/29
 */
public class MenuConvertHelper {

    /**
     * 系统菜单转菜单树
     * @param sysMenu
     * @return
     */
    public static MenuTree convertSysMenuToMenuTree(SysMenu sysMenu) {
        MenuTree menuTree = new MenuTree();
        menuTree.setId(sysMenu.getId());
        menuTree.setName(sysMenu.getName());
        menuTree.setPId(sysMenu.getParentId());
        menuTree.setIcon(sysMenu.getIcon());
        menuTree.setUrl(sysMenu.getUrl());
        menuTree.setDescription(sysMenu.getDescription());
        menuTree.setCodeType(sysMenu.getType());
        menuTree.setCode(sysMenu.getCode());
        if (sysMenu.getIsopen() != null && sysMenu.getIsopen() == 1) {
            menuTree.setChecked(true);
        } else {
            menuTree.setChecked(false);
        }
        return menuTree;
    }

    /**
     * 树型转换
     * @param sysMenus
     * @return
     */
    public static List<MenuTree> convertSysMenuListToMenuTreeList(List<SysMenu> sysMenus)
    {
        List<MenuTree> trees = Lists.newArrayList();
        if(!CollectionUtils.isEmpty(sysMenus))
        {
            for (SysMenu menu:sysMenus) {
                trees.add(convertSysMenuToMenuTree(menu));
            }
        }
        return trees;
    }

    /**
     * 系统菜单列表转前台展示菜单列表
     * @param sysMenuList
     * @return
     */
    public static List<Menu> convertSysMenuListToMenuList(List<SysMenu> sysMenuList) {
        return BeanUtil.do2bo4List(sysMenuList,Menu.class);
    }

}
