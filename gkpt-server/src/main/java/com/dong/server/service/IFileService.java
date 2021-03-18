package com.dong.server.service;

import java.io.InputStream;

/**
 * @ClassName: IFileService
 * @Author: caojingdong
 * @Description:
 * @Date: 2021/3/18 11:17
 */

public interface IFileService {
    //流式上传
    String upload(InputStream inputStream, String module, String originalFilename);

    //删除文件
    void removeFile(String url);
}
