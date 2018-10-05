package org.chens.admin.facade.impl;

import org.chens.admin.param.LoginRequest;
import org.chens.admin.param.User;
import org.chens.admin.param.UserTokenVo;
import org.chens.admin.service.AuthFacade;
import org.chens.admin.service.SysUserFacade;
import org.chens.core.exception.BaseExceptionEnum;
import org.chens.core.vo.Result;
import org.chens.core.vo.UserInfo;
import org.chens.framework.login.service.AbstractAuthService;
import org.chens.framework.login.vo.AuthRequest;
import org.chens.framework.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 授权服务
 *
 * @author songchunlei
 * @since 2018/10/5
 */
public class AuthFacadeImpl extends AbstractAuthService implements AuthFacade {

    @Autowired
    private SysUserFacade sysUserFacade;

    @Override
    public Result<UserTokenVo> login(LoginRequest loginRequest) {
        AuthRequest authRequest = BeanUtil.do2bo(loginRequest,AuthRequest.class);
        if(authRequest == null){
            return Result.getError(BaseExceptionEnum.REQUEST_NULL);
        }
        Result<UserInfo> userInfoResult = super.login(authRequest);
        if(!userInfoResult.isSuccess()){
            return Result.getError(userInfoResult.getCode(),userInfoResult.getMsg());
        }
        UserInfo userInfo = userInfoResult.getData();
        //TODO 待更新
        UserTokenVo userTokenVo = new UserTokenVo(null, null, null,userInfo);
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
            //TODO 待更新TOKEN
            UserInfo userInfo = new UserInfo(user.getId(), user.getName(), user.getUsername(), user.getTenantId(),
                    null);
            return userInfo;
        }
        return null;
    }
}
