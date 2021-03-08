package com.dong.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dong.server.pojo.Admin;
import com.dong.server.pojo.Menu;
import com.dong.server.pojo.ResponseBean;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caojingdong
 * @since 2021-03-04
 */
public interface IAdminService extends IService<Admin> {

    ResponseBean login(String username, String password, String code, HttpServletRequest request);

    Admin getAdminByUserName(String username);

    List<Menu> getMenuById();
}
