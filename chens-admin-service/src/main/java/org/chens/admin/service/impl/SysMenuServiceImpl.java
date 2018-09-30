package org.chens.admin.service.impl;

import org.chens.admin.entity.SysMenu;
import org.chens.admin.mapper.SysMenuMapper;
import org.chens.admin.service.ISysMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chunlei.song@live.com123
 * @since 2018-03-09
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {


    @Override
    public List<SysMenu> getMenuListByUserId(String userId) {
        return baseMapper.getMenuListByUserId(userId);
    }
}
