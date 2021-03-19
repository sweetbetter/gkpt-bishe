package com.dong.server.service.course;


import com.baomidou.mybatisplus.extension.service.IService;
import com.dong.server.pojo.course.Course;
import com.dong.server.pojo.course.vm.CourseInfoForm;

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
}
