package org.chens.admin.param;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单
 *
 * @author songchunlei
 * @since 2018/9/29
 */
@Data
public class Menu implements Serializable {

    private static final long serialVersionUID = -4080397496633152231L;

    /**
     * 父菜单id
     */
    private String parentId;

    /**
     * 菜单类型
     */
    private String type;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 访问地址
     */
    private String url;

    /**
     * 描述
     */
    private String description;

    /**
     * 排序
     */
    private Integer seq;

    /**
     * 是否打开 1打开 0不打开
     */
    private Integer isopen;

    /**
     * 图标
     */
    private String icon;

    /**
     * 子菜单
     */
    private List<Menu> childList;
}
