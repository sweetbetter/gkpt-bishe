package com.dong.server.controller;

import com.dong.server.basic.exception.BasicException;
import com.dong.server.service.IFileService;
import com.dong.server.util.ExceptionUtils;
import com.dong.server.util.R;
import com.dong.server.util.ResultCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @ClassName: FileController
 * @Author: caojingdong
 * @Description: 阿里云文件管理相关接口
 * @Date: 2021/3/18 13:15
 */
@Api(description = "阿里云文件管理")
@CrossOrigin
@RestController
@RequestMapping("/oss/file")
@Slf4j
public class FileController {

    @Autowired
    private IFileService fileService;

    @ApiOperation("文件上传")
    @PostMapping("upload")
    public R upload(
            @ApiParam(value = "文件", required = true) @RequestParam("file") MultipartFile file,
            @ApiParam(value = "模块(目录)", required = true) @RequestParam("module") String module) {
        try {
        InputStream inputStream = null;
        inputStream = file.getInputStream();

        String originalFilename = file.getOriginalFilename();
            String uploadUrl = fileService.upload(inputStream, module, originalFilename);

            return R.ok().message("文件上传成功").data("url", uploadUrl);
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
          throw new BasicException(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }
    }
}
