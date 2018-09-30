package org.chens.admin.facade.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.chens.admin.entity.SysMenu;
import org.chens.admin.param.Menu;
import org.chens.admin.service.ISysMenuService;
import org.chens.admin.service.SysMenuFacade;
import org.chens.admin.param.MenuTree;
import org.chens.admin.util.MenuConvertHelper;
import org.chens.core.tree.BaseTree;
import org.chens.core.tree.TreeUtil;
import org.chens.core.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * 菜单服务实现
 *
 * @author songchunlei
 * @since 2018/9/29
 */
public class SysMenuFacadeImpl implements SysMenuFacade{

    @Autowired
    private ISysMenuService sysMenuService;

    @Override
    public Result<List<MenuTree>> getAllMenuTreeList() {
        //获取系统树列表
        List<SysMenu> sysMenus =  sysMenuService.selectList(new EntityWrapper<>(new SysMenu()));
        //获取树列表
        List<MenuTree> menuTreeList = MenuConvertHelper.convertSysMenuListToMenuTreeList(sysMenus);
        //构建树结构
        List<MenuTree> trees = TreeUtil.build(menuTreeList, BaseTree.BASE_TREE_ROOT);
        return Result.getSuccess(trees);
    }
}
