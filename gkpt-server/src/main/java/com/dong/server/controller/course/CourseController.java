package com.dong.server.controller.course;


import com.dong.server.pojo.course.vm.CourseInfoForm;
import com.dong.server.service.course.ICourseService;
import com.dong.server.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
