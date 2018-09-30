package org.chens.admin.validator;

import org.chens.admin.entity.SysDictType;
import org.chens.admin.service.ISysDictTypeService;
import org.chens.app.validator.BaseValidator;
import org.chens.framework.util.ApplicationContextUtil;

/**
 * 字典自定义校验
 *
 * @author songchunlei@qq.com
 * @since 2018/3/29
 */
public class DictValidator extends BaseValidator<ISysDictTypeService, SysDictType> {

    /**
     * 自定义初始化
     */
    public DictValidator() {
        if (service == null) {
            service = ApplicationContextUtil.getBeanByClass(ISysDictTypeService.class);
        }
    }

    public boolean check(SysDictType sysDictType) throws Exception {
        SysDictType query = new SysDictType();
        query.setTypeCode(sysDictType.getTypeCode());
        query.setId(sysDictType.getId());
        return this.checkIsNotExist(sysDictType);
    }
}
