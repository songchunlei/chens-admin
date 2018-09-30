package org.chens.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.chens.app.annotation.InsertValid;
import org.chens.app.annotation.UpdateValid;
import org.chens.app.vo.BaseEntity;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author chunlei.song@live.com
 * @since 2018-03-09
 */
@Data
@TableName("sys_menu")
public class SysMenu extends BaseEntity<SysMenu> {

    private static final long serialVersionUID = -8717557744551519118L;

    @TableField("parent_id")
    @NotNull(message = "{menu.parentId.null}", groups = { InsertValid.class, UpdateValid.class })
    private String parentId;

    @NotNull(message = "{menu.type.null}", groups = { InsertValid.class, UpdateValid.class })
    private String type;

    @NotNull(message = "{menu.name.null}", groups = { InsertValid.class, UpdateValid.class })
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
    @NotNull(message = "{menu.seq.null}", groups = { InsertValid.class, UpdateValid.class })
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
     * 菜单编码
     */
    @NotNull(message = "{menu.code.null}", groups = { InsertValid.class, UpdateValid.class })
    private String code;

}
