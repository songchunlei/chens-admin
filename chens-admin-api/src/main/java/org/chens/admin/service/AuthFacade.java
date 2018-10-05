package org.chens.admin.service;

import org.chens.admin.param.LoginRequest;
import org.chens.admin.param.UserTokenVo;
import org.chens.core.vo.Result;

/**
 * 授权服务
 *
 * @author songchunlei
 * @since 2018/10/5
 */
public interface AuthFacade {

    /**
     * 登陆
     * @param loginRequest
     * @return
     */
    Result<UserTokenVo> login(LoginRequest loginRequest);

    /**
     * 登出
     * @return
     */
    Result<Boolean> logOut();
}
