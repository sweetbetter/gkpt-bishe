package com.dong.server.controller.course;


import com.dong.server.basic.exception.BasicException;
import com.dong.server.pojo.course.vm.SubjectVM;
import com.dong.server.service.course.ISubjectService;
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
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author caojingdong
 * @since 2021-03-18
 */
@Api("课程分类管理")
@CrossOrigin
@RestController
@RequestMapping("/course/subject")
@Slf4j
public class SubjectController {
    @Autowired
    private ISubjectService subjectService;

    @ApiOperation("批量导入课程分类")
    @PostMapping("import")
    public R subjectImport(@ApiParam(value = "Excel文件", required = true) @RequestParam("file") MultipartFile file){
        try {
            InputStream inputStream=file.getInputStream();
            subjectService.subjectImport(inputStream);
            return R.ok().message("导入成功");
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new BasicException(ResultCodeEnum.EXCEL_DATA_IMPORT_ERROR);
        }
    }

    @ApiOperation("获取数据列表")
    @GetMapping("subjectList")
    public R subjectList(){
        List<SubjectVM> subjectList=subjectService.subjectList();
        return R.ok().data("lists",subjectList);
    }

}
