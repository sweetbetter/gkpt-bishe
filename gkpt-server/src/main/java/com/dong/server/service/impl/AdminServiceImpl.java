package com.dong.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.server.config.secutity.JwtTokenUtil;
import com.dong.server.mapper.AdminMapper;
import com.dong.server.pojo.Admin;
import com.dong.server.pojo.Menu;
import com.dong.server.pojo.ResponseBean;
import com.dong.server.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caojingdong
 * @since 2021-03-04
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    //登陆后返回token
    @Override
    public ResponseBean login(String username, String password, String code, HttpServletRequest request) {
        String captcha = (String) request.getSession().getAttribute("captcha");
        if (StringUtils.isEmpty(code)||!captcha.equalsIgnoreCase(code)){
            return ResponseBean.error("验证码输入错误，请重新输入！");
        }
        UserDetails userDetails=userDetailsService.loadUserByUsername(username);
        if(null==userDetails|| !passwordEncoder.matches(password,userDetails.getPassword())){
            return ResponseBean.error("用户名或密码错误");
        }
        if(!userDetails.isEnabled()){
            return ResponseBean.error("用户已被禁用");
        }
        //跟新登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //生成token
        String token=jwtTokenUtil.generateToken(userDetails);
        Map<String,String> tokenMap=new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return ResponseBean.success("登录成功",tokenMap);
    }

    @Override
    public Admin getAdminByUserName(String username) {
        //判断用户信息 以后在补充
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",username).eq("enabled",true));
    }

    //通过用户id获取菜单列表
    @Override
    public List<Menu> getMenuById() {
        return adminMapper.getMenuById(((Admin)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }
}
