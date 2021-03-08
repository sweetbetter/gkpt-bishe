package com.dong.server.config;

import org.mybatis.spring.annotation.MapperScan;
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

}
