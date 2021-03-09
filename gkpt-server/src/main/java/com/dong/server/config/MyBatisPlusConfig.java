package com.dong.server.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName: MyBatisPlusConfig
 * @Author: caojingdong
 * @Description: mybatisplus配置
 * @Date: 2021/3/8 23:58
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.dong.server.mapper")
public class MyBatisPlusConfig {


    //分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
