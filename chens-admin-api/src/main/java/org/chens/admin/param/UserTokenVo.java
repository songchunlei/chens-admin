package org.chens.admin.param;

import lombok.Data;
import org.chens.core.vo.UserInfo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 自定义带用户信息的JWT封装反馈
 *
 * @author songchunlei@qq.com
 * @since 2018/3/19
 */
@Data
public class UserTokenVo implements Serializable {
    private static final long serialVersionUID = 1996959159472920120L;

    private String accessToken;

    private List<MenuTree> menus;

    private Map<String, MenuTree> all;

    private UserInfo user;

    public UserTokenVo(String accessToken) {
        super();
        this.accessToken = accessToken;
    }

    public UserTokenVo(String accessToken, List<MenuTree> menus, Map<String, MenuTree> all, UserInfo user) {
        this.accessToken = accessToken;
        this.menus = menus;
        this.all = all;
        this.user = user;
    }

}
