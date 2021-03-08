package com.dong.server.controller;


import com.dong.server.pojo.Menu;
import com.dong.server.service.IAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author caojingdong
 * @since 2021-03-08
 */
@RestController
@RequestMapping("/system/cfg")
public class MenuController {
    @Autowired
    private IAdminService adminService;

    @ApiOperation("通过用户id查询菜单列表")
    @GetMapping("/menu")
    public List<Menu> getMenuById(){
        return adminService.getMenuById();
    }


}
