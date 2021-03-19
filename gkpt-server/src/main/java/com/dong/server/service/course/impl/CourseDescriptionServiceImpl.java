package com.dong.server.service.course.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.server.mapper.course.CourseDescriptionMapper;
import com.dong.server.pojo.course.CourseDescription;
import com.dong.server.service.course.ICourseDescriptionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author caojingdong
 * @since 2021-03-19
 */
@Service
public class CourseDescriptionServiceImpl extends ServiceImpl<CourseDescriptionMapper, CourseDescription> implements ICourseDescriptionService {

}
