package com.dong.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.dong.server.mapper.MenuMapper;
import com.dong.server.pojo.Menu;
import com.dong.server.service.IMenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caojingdong
 * @since 2021-03-08
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
