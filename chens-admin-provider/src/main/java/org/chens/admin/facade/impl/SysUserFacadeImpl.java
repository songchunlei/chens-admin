package org.chens.admin.facade.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.chens.admin.entity.SysMenu;
import org.chens.admin.entity.SysUser;
import org.chens.admin.param.*;
import org.chens.admin.service.*;
import org.chens.admin.util.MenuConvertHelper;
import org.chens.core.exception.BaseExceptionEnum;
import org.chens.core.tree.BaseTree;
import org.chens.core.tree.TreeUtil;
import org.chens.core.util.StringUtils;
import org.chens.core.vo.Result;
import org.chens.framework.auth.exception.AuthExceptionEnum;
import org.chens.framework.security.IPasswordCoder;
import org.chens.framework.security.impl.PasswordCoderByBcrypt;
import org.chens.framework.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 系统用户服务实现
 *
 * @author songchunlei
 * @since 2018/9/30
 */
public class SysUserFacadeImpl implements SysUserFacade {

    @Autowired
    private ISysMenuService sysMenuService;

    @Autowired
    private ISysUserService sysUserService;

    private IPasswordCoder passwordCoder = new PasswordCoderByBcrypt();

    @Override
    public Result<List<Menu>> getMenuListByUserId(String userId) {
        // 获取系统树列表
        List<SysMenu> sysMenuList = this.getMenuListByUserIdFromDb(userId);
        // 获取树列表
        List<Menu> menuList = MenuConvertHelper.convertSysMenuListToMenuList(sysMenuList);
        return Result.getSuccess(menuList);
    }

    @Override
    public Result<List<MenuTree>> getMenuTreeListByUserId(String userId) {
        // 获取系统树列表
        List<SysMenu> menuList = this.getMenuListByUserIdFromDb(userId);
        // 获取树列表
        List<MenuTree> menuTreeList = MenuConvertHelper.convertSysMenuListToMenuTreeList(menuList);
        // 构建树结构
        List<MenuTree> trees = TreeUtil.build(menuTreeList, BaseTree.BASE_TREE_ROOT);
        return Result.getSuccess(trees);
    }

    @Override
    public Result<User> findByUsername(String userName, String password) {
        //TODO 待更新异常
        if(StringUtils.isEmpty(userName)){
            return Result.getError(AuthExceptionEnum.AUTH_REQUEST_NO_USERNAME);
        }
        if(StringUtils.isEmpty(password)){
            return Result.getError(AuthExceptionEnum.AUTH_REQUEST_NO_USERNAME);
        }
        SysUser query = new SysUser();
        query.setUsername(userName);
        List<SysUser> users = sysUserService.selectList(new EntityWrapper<>(query));
        if (users != null && users.size() > 0) {
            for (SysUser user : users) {
                if (passwordCoder.matches(password, user.getPassword())) {
                    return Result.getSuccess(BeanUtil.do2bo(user,User.class));
                }
            }
        }
        return Result.getError(BaseExceptionEnum.NO_DATA);
    }

    /**
     * 从数据库根据用户id抽取菜单
     * 
     * @param userId
     * @return
     */
    private List<SysMenu> getMenuListByUserIdFromDb(String userId) {
        List<SysMenu> sysMenuList = sysMenuService.getMenuListByUserId(userId);
        return sysMenuList;
    }
}
