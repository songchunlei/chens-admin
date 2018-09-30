package org.chens.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.chens.app.vo.BaseEntity;

import javax.validation.constraints.NotNull;

/**
 * @author songchunlei
 * @since 2018-03-08
 */
@Data
@TableName("sys_dict_type")
public class SysDictType extends BaseEntity<SysDictType> {

    private static final long serialVersionUID = 6195077992271624363L;

    @NotNull(message = "{dictType.code.null}")
    @TableField("type_code")
    private String typeCode;

    @NotNull(message = "{dictType.name.null}")
    @TableField("type_name")
    private String typeName;

}
