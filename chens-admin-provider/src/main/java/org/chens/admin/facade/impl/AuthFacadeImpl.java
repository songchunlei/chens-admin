package org.chens.admin.facade.impl;

import org.chens.admin.entity.SysMenu;
import org.chens.admin.enums.SysMenuEnum;
import org.chens.admin.param.LoginRequest;
import org.chens.admin.param.MenuTree;
import org.chens.admin.param.User;
import org.chens.admin.param.UserTokenVo;
import org.chens.admin.service.AuthFacade;
import org.chens.admin.service.ISysMenuService;
import org.chens.admin.service.SysUserFacade;
import org.chens.admin.util.MenuConvertHelper;
import org.chens.core.exception.BaseExceptionEnum;
import org.chens.core.tree.BaseTree;
import org.chens.core.tree.TreeUtil;
import org.chens.core.vo.Result;
import org.chens.core.vo.UserInfo;
import org.chens.framework.auth.exception.AuthExceptionEnum;
import org.chens.framework.auth.service.AbstractAuthService;
import org.chens.framework.auth.vo.AuthRequest;
import org.chens.framework.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 授权服务
 *
 * @author songchunlei
 * @since 2018/10/5
 */
public class AuthFacadeImpl extends AbstractAuthService implements AuthFacade {

    @Autowired
    private SysUserFacade sysUserFacade;

    @Autowired
    private ISysMenuService sysMenuService;

    @Override
    public Result<UserTokenVo> login(LoginRequest loginRequest) {
        AuthRequest authRequest = BeanUtil.do2bo(loginRequest, AuthRequest.class);
        if (authRequest == null) {
            return Result.getError(BaseExceptionEnum.REQUEST_NULL);
        }
        Result<UserInfo> userInfoResult = super.login(authRequest);
        if (!userInfoResult.isSuccess()) {
            return Result.getError(userInfoResult.getCode(), userInfoResult.getMsg());
        }
        UserInfo userInfo = userInfoResult.getData();
        if (userInfo == null) {
            return Result.getError(AuthExceptionEnum.AUTH_REQUEST_NO_USERNAME);
        }
        // 获取菜单列表
        List<SysMenu> sysMenus = sysMenuService.getMenuListByUserId(userInfo.getId());
        // 全量打平菜单树
        Map<String, MenuTree> all = new HashMap<String, MenuTree>();
        // 菜单嵌套树
        List<MenuTree> trees = new ArrayList<>();
        // 循环
        if (!CollectionUtils.isEmpty(sysMenus)) {
            for (SysMenu menu : sysMenus) {
                MenuTree menuTree = MenuConvertHelper.convertSysMenuToMenuTree(menu);
                trees.add(menuTree);
                // 当菜单类型为页面时，放入子菜单（不克隆）
                if (SysMenuEnum.PAGE.getCode().equals(menu.getType())) {
                    all.put(menu.getCode(), menuTree);
                } else {
                    all.put(menu.getCode(), menuTree.clone());
                }
            }
        }
        // 构建树结构
        List<MenuTree> menus = TreeUtil.build(trees, BaseTree.BASE_TREE_ROOT);
        // 返回JWTToken
        UserTokenVo userTokenVo = new UserTokenVo(userInfo.getToken(), menus, all, userInfo);
        return Result.getSuccess(userTokenVo);
    }

    @Override
    public Result<Boolean> logOut() {
        return super.logout();
    }

    @Override
    protected UserInfo getUserInfo(AuthRequest authRequest) {
        Result<User> userResult = sysUserFacade.findByUsername(authRequest.getUserName(), authRequest.getPassword());
        if (userResult.isSuccess()) {
            User user = userResult.getData();
            // TODO 待更新TOKEN
            UserInfo userInfo = new UserInfo(user.getId(), user.getName(), user.getUsername(), user.getTenantId(),
                    null);
            return userInfo;
        }
        return null;
    }

}
