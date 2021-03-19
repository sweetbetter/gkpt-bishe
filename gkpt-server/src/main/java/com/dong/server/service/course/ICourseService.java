package com.dong.server.service.course;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dong.server.pojo.course.Course;
import com.dong.server.pojo.course.vm.CourseFormVM;
import com.dong.server.pojo.course.vm.CourseInfoForm;
import com.dong.server.pojo.course.vm.CourseQueryVM;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author caojingdong
 * @since 2021-03-19
 */
public interface ICourseService extends IService<Course> {

    Long saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseById(String id);

    void updateCourseInfoById(CourseInfoForm courseInfoForm);

    IPage<CourseFormVM> selectPage(Long page, Long limit, CourseQueryVM courseQueryVM);
}
