package com.dong.server.controller;

import com.dong.server.pojo.Admin;
import com.dong.server.pojo.AdminLoginParam;
import com.dong.server.pojo.ResponseBean;
import com.dong.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @ClassName: LoginController
 * @Author: 努力努力再努力d
 * @Description: 登录
 * @Date: 2021/3/5 0:50
 * @Version: 1.0
 */
@Api(tags = "loginController")
@RestController
public class LoginController {
    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "登录后返回token")
    @PostMapping("/login")
    public ResponseBean login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request){
        return adminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),adminLoginParam.getCode(),request);
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public ResponseBean logout(){
        return ResponseBean.success("注销成功");
    }

    @ApiOperation(value = "获取当前用户信息")
    @PostMapping("/admin/info")
    public Admin getInfo(Principal principal){
        if(null==principal){
            return null;
        }
        String username=principal.getName();
        Admin admin=adminService.getAdminByUserName(username);
        admin.setPassword(null);
        return admin;
    }
}
