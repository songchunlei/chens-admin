package org.chens.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.chens.app.annotation.InsertValid;
import org.chens.app.annotation.UpdateValid;
import org.chens.app.vo.BaseEntity;

import javax.validation.constraints.NotNull;

/**
 * 实体
 *
 * @author chunlei.song@live.com
 * @since 2018-03-12
 */
@TableName("sys_dict")
@Data
public class SysDict extends BaseEntity<SysDict> {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "{dict.type.null}", groups = {InsertValid.class, UpdateValid.class})
    private String type;
    @NotNull(message = "{dict.val.null}", groups = {InsertValid.class, UpdateValid.class})
    private String val;
    @NotNull(message = "{dict.text.null}", groups = {InsertValid.class, UpdateValid.class})
    private String text;
    @NotNull(message = "{dict.seq.null}", groups = {InsertValid.class, UpdateValid.class})
    private Integer seq;
    @TableField("DESCRIPTION")
    private String description;
    @NotNull(message = "{dict.parentId.null}", groups = {InsertValid.class, UpdateValid.class})
    @TableField("PARENT_ID")
    private String parentId;
    @TableField("is_delete")
    private String isDelete;
    private String exp1;
    private String exp2;

}
