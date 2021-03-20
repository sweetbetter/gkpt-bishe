package com.dong.server.controller.course;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dong.server.pojo.course.vm.CourseFormVM;
import com.dong.server.pojo.course.vm.CourseInfoForm;
import com.dong.server.pojo.course.vm.CoursePulishFormVM;
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
    public R getById(@ApiParam(value = "课程ID", required = true) @PathVariable Long id) {
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

    @ApiOperation(value = "根据ID删除课程")
    @DeleteMapping("remove/{id}")
    public R removeById(@ApiParam("讲师id") @PathVariable Long id){
        //TODO: 删除对应云服务视频

        //TODO： 删除封面

        //删除课程及相关数据库数据
        boolean result2= courseService.removeCoverById(id);
        //removeCourseById删除课程相关关联表的数据
        boolean result= courseService.removeCourseById(id);//失败返回0
        if(result){
            return R.ok().message("删除成功");
        }else{
            return R.error().message("课程不存在");
        }
    }

    @ApiOperation("根据id发布课程")
    @PutMapping("publishCourse/{id}")
    public R publishCourseById(
@ApiParam(value = "课程ID", required = true)
        @PathVariable Long id){
        boolean result = courseService.publishCourseById(id);
        if (result) {
            return R.ok().message("发布成功");
        } else {
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("根据ID获取课程发布信息")
    @GetMapping("getPublishInfo/{id}")
    public R getCoursePublishVoById(@ApiParam(value = "课程ID", required = true) @PathVariable Long id){
        CoursePulishFormVM coursePublishVo = courseService.getCoursePulishVMById(id);
        if (coursePublishVo != null) {
            return R.ok().data("item", coursePublishVo);
        } else {
            return R.error().message("数据不存在");
        }
    }
}
