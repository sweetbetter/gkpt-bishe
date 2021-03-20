package com.dong.server.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.dong.server.service.IFileService;
import com.dong.server.util.OssProperties;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

/**
 * @ClassName: FileServiceImpl
 * @Author: caojingdong
 * @Description:
 * @Date: 2021/3/18 11:20
 */
@Service
@Slf4j
public class FileServiceImpl implements IFileService {
    @Autowired
    OssProperties ossProperties;

//    private String endpoint = ossProperties.getEndpoint();
//    private String keyid = ossProperties.getKeyid();
//    private String keysecret = ossProperties.getKeysecret();
//    private String bucketname = ossProperties.getBucketname();

    @Override
    public String upload(InputStream inputStream, String module, String originalFilename) {
         String endpoint = ossProperties.getEndpoint();
         String keyid = ossProperties.getKeyid();
        String keysecret = ossProperties.getKeysecret();
        String bucketname = ossProperties.getBucketname();
        OSS ossClient = new OSSClientBuilder().build(endpoint, keyid, keysecret);
        //判断bucket是否存在
        if(!ossClient.doesBucketExist(bucketname)){
            ossClient.createBucket(bucketname);
            ossClient.setBucketAcl(bucketname, CannedAccessControlList.PublicRead);
        }

        //构建objectName：文件路径 avatar/2020/04/15/default.jpg
        String folder = new DateTime().toString("yyyy");
        String fileName = UUID.randomUUID().toString();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".")); //后缀名
        String objectname = module +"/" + folder + "/" + fileName + fileExtension;

        // 上传文件流。
        ossClient.putObject(bucketname, objectname, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();

        //返回url
        //https://bucketname.oss-cn-beijing.aliyuncs.com/avatar/default.jpg
        return "https://" + bucketname + "." + endpoint + "/" + objectname;
    }

    @Override
    public void removeFile(String url) {
        String endpoint = ossProperties.getEndpoint();
        String keyid = ossProperties.getKeyid();
        String keysecret = ossProperties.getKeysecret();
        String bucketname = ossProperties.getBucketname();
        OSS ossClient = new OSSClientBuilder().build(endpoint, keyid, keysecret);
        //https://gkpt-file.oss-cn-beijing.aliyuncs.com/avatar/2021/adf10064-8b0c-41d3-bb2c-28c76b28f7e0.jpg
        String host = "https://" + bucketname + "." + endpoint + "/";
        String objectName=null;
        if(StringUtils.hasText(url)){
             objectName = url.substring(host.length());
        }else return;
        log.info(host+"1name"+objectName);
        ossClient.deleteObject(bucketname,objectName);
        ossClient.shutdown();
    }
}
