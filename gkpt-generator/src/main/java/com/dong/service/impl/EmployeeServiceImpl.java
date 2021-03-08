package com.dong.service.impl;

import com.dong.pojo.Employee;
import com.dong.mapper.EmployeeMapper;
import com.dong.service.IEmployeeService;
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
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
