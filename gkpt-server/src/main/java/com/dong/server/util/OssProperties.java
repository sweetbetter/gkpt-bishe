package com.dong.server.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName: OssProperties
 * @Author: caojingdong
 * @Description: 读取oss相关常量
 * @Date: 2021/3/18 11:15
 */
@Component
@Data
@ConfigurationProperties(prefix="aliyun.oss")
public class OssProperties {
    private String endpoint;
    private String keyid;
    private String keysecret;
    private String bucketname;

}
