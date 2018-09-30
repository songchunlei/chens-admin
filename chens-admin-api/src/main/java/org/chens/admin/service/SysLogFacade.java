package org.chens.admin.service;

import org.chens.admin.param.Log;
import org.chens.core.vo.Result;

/**
 * 日志服务
 *
 * @author songchunlei
 * @since 2018/9/29
 */
public interface SysLogFacade {

    /**
     * 插入日志
     * 
     * @param log
     * @return
     */
    Result<Boolean> insert(Log log);
}
