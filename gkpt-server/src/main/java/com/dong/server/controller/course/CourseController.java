package com.dong.server.controller.course;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dong.server.pojo.course.vm.CourseFormVM;
import com.dong.server.pojo.course.vm.CourseInfoForm;
import com.dong.server.pojo.course.vm.CourseQueryVM;
import com.dong.server.service.course.ICourseService;
import com.dong.server.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author caojingdong
 * @since 2021-03-19
 */
@Api("课程管理")
@CrossOrigin
@RestController
@RequestMapping("/course")
@Slf4j
public class CourseController {

    @Autowired
    private ICourseService courseService;


    @ApiOperation("新增课程")
    @PostMapping("saveCourse")
    public R saveCourseInfo(@ApiParam(value = "课程基本信息", required = true) @RequestBody CourseInfoForm courseInfoForm) {
        Long courseId = courseService.saveCourseInfo(courseInfoForm);
        return R.ok().data("courseId", courseId).message("保存成功");
    }

    //回显
    @ApiOperation("通过ID查询课程")
    @GetMapping("courseInfo/{id}")
    public R getById(@ApiParam(value = "课程ID", required = true) @PathVariable String id) {
        CourseInfoForm courseInfoForm = courseService.getCourseById(id);
        if (courseInfoForm != null) {
            return R.ok().data("data", courseInfoForm);
        } else {
            return R.ok().message("数据不存在");
        }
    }

    @ApiOperation("更新课程")
    @PutMapping("updateInfo")
    public R getById(
            @ApiParam(value = "课程ID", required = true)
            @RequestBody CourseInfoForm courseInfoForm) {

        courseService.updateCourseInfoById(courseInfoForm);

        return R.ok().message("修改成功");
    }

    @ApiOperation("分页课程列表")
    @GetMapping("list/{page}/{limit}")
    public R listPage(@ApiParam(value = "当前页码", required = true) @PathVariable Long page,
                      @ApiParam(value = "每页记录数", required = true) @PathVariable Long limit,
                      @ApiParam("课程列表查询对象") CourseQueryVM courseQueryVM){

        IPage<CourseFormVM> pageModel = courseService.selectPage(page, limit, courseQueryVM);
        List<CourseFormVM> records = pageModel.getRecords();
        long total = pageModel.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }


}
