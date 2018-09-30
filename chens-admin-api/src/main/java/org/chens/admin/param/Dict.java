package org.chens.admin.param;

import lombok.Data;

/**
 * 字典
 *
 * @author songchunlei
 * @since 2018/9/29
 */
@Data
public class Dict {
    /**
     * 类型
     */
    private String type;

    /**
     * 类型名称
     */
    private String typeName;
    /**
     * 值
     */
    private String val;
    /**
     * 文本
     */
    private String text;
    /**
     * 排序
     */
    private Integer seq;
    /**
     * 描述
     */
    private String description;
    /**
     * 父节点
     */
    private String parentId;

    private String exp1;
    private String exp2;
}
