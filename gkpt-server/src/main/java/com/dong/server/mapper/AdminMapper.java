package com.dong.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dong.server.pojo.Admin;
import com.dong.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author caojingdong
 * @since 2021-03-04
 */
public interface AdminMapper extends BaseMapper<Admin> {

    List<Menu> getMenuById(Integer id);
}
