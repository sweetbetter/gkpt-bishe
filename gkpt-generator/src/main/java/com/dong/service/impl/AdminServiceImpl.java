package com.dong.service.impl;

import com.dong.pojo.Admin;
import com.dong.mapper.AdminMapper;
import com.dong.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

}
