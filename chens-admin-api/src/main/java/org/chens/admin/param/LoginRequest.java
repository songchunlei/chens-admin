package org.chens.admin.param;

import lombok.Data;

import java.io.Serializable;

/**
 * 登陆请求
 *
 * @author songchunlei
 * @since 2018/10/5
 */
@Data
public class LoginRequest implements Serializable {

    private String userName;
    private String password;
}
