package com.dong.server.mapper.course;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dong.server.pojo.course.Course;
import com.dong.server.pojo.course.vm.CourseFormVM;
import com.dong.server.pojo.course.vm.CoursePulishFormVM;
import com.dong.server.pojo.course.vm.CourseQueryVM;
import org.apache.ibatis.annotations.Param;

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
    List<CourseFormVM> selectPageByQueryVM(Page<CourseFormVM> courseFormVMPage, @Param(Constants.WRAPPER)QueryWrapper<Course> queryWrapper);

    CoursePulishFormVM getCoursePulishVMById(Long id);
}
