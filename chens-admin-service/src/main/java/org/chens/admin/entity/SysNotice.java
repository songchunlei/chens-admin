package org.chens.admin.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.chens.app.vo.BaseEntity;

import javax.validation.constraints.NotNull;

/**
 *
 * 实体
 *
 * @author chunlei.song@live.com
 * @since 2018-03-12
 */
@TableName("sys_notice")
public class SysNotice extends BaseEntity<SysNotice> {

    private static final long serialVersionUID = 1846947048677691338L;

    /**
     * 标题
     */
    @NotNull
    private String title;

    /**
     * 类型
     */
    @NotNull
    private String type;

    /**
     * 内容
     */
    @NotNull
    private String content;
}
