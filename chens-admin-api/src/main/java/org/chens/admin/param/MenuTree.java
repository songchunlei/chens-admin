package org.chens.admin.param;

import lombok.Data;
import org.chens.core.tree.BaseTree;

import java.util.List;

/**
 * 菜单树
 *
 * @author songchunlei@qq.com
 * @since 2018/3/19
 */
@Data
public class MenuTree extends BaseTree<MenuTree> {
    private static final long serialVersionUID = -7958538067755829735L;

    private String icon;

    private String url;

    private String description;

    private String code;

    /**
     * id
     */
    protected String id;
    /**
     * 父id
     */
    protected String pId;
    /**
     * 名称
     */
    protected String name;
    /**
     * 分类
     */
    protected String codeType;
    /**
     * 是否选中
     */
    protected boolean checked;
    /**
     * 子列表
     */
    protected List<MenuTree> children;



    public MenuTree() {

    }

    public MenuTree(String icon, String url, String description) {
        this.icon = icon;
        this.url = url;
        this.description = description;
    }

    public MenuTree(String id, String pId, String name, String codeType, boolean open, String icon, String url,
            String description) {
        super(id, pId, name, codeType, open);
        this.icon = icon;
        this.url = url;
        this.description = description;
    }

    @Override
    public MenuTree clone() {
        return new MenuTree(this.id, this.pId, this.name, this.codeType, this.checked, this.icon, this.url,
                this.description);
    }
}
