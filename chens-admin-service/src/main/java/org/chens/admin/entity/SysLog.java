package org.chens.admin.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.chens.app.vo.BaseEntity;

/**
 * 实体
 *
 * @author chunlei.song@live.com
 * @since 2018-03-24
 */
@Data
@TableName("sys_log")
public class SysLog extends BaseEntity<SysLog> {

    private static final long serialVersionUID = -1075228271014776803L;

    /**
     * 操作类型
     */
    private String opt;

    /**
     * 请求人地址
     */
    private String host;

    /**
     * 请求串
     */
    private String url;

    public SysLog() {
    }
}
