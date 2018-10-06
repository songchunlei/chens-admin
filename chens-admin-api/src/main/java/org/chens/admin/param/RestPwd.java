package org.chens.admin.param;

import lombok.Data;

import java.io.Serializable;

/**
 * 重置密码
 *
 * @author songchunlei@qq.com
 * @since 2018/4/18
 */
@Data
public class RestPwd implements Serializable {

    /**
     * 用户ID
     */
    String userId;

    /**
     * 是否随机
     */
    boolean random;

    public RestPwd() {

    }

    public RestPwd(String userId, boolean random) {
        this.userId = userId;
        this.random = random;
    }
}
