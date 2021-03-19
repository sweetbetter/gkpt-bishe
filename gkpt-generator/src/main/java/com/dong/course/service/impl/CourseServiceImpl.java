package com.dong.course.service.impl;

import com.dong.course.pojo.Course;
import com.dong.course.mapper.CourseMapper;
import com.dong.course.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author caojingdong
 * @since 2021-03-19
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

}
