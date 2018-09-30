package org.chens.admin.param;

import lombok.Data;

/**
 * 日志
 *
 * @author songchunlei
 * @since 2018/9/29
 */
@Data
public class Log {
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
}
