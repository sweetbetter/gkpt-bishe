package com.dong.server.mapper.course;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dong.server.pojo.course.Course;
import com.dong.server.pojo.course.vm.CourseFormVM;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author caojingdong
 * @since 2021-03-19
 */
public interface CourseMapper extends BaseMapper<Course> {
    List<CourseFormVM> selectPageByQueryVM();

}
