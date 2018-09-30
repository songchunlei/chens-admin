package org.chens.admin.facade.impl;

import org.chens.admin.entity.SysLog;
import org.chens.admin.param.Log;
import org.chens.admin.service.ISysLogService;
import org.chens.admin.service.SysLogFacade;
import org.chens.core.vo.Result;
import org.chens.framework.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 日志服务实现
 *
 * @author songchunlei
 * @since 2018/9/30
 */
public class SysLogFacadeImpl implements SysLogFacade {

    @Autowired
    private ISysLogService sysLogService;

    @Override
    public Result<Boolean> insert(Log log) {
        SysLog sysLog = BeanUtil.do2bo(log,SysLog.class);
        return Result.getSuccess(sysLogService.insert(sysLog));
    }
}
