package com.dong.server.service.course.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.server.mapper.course.CourseCollectMapper;
import com.dong.server.pojo.course.CourseCollect;
import com.dong.server.service.course.ICourseCollectService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程收藏 服务实现类
 * </p>
 *
 * @author caojingdong
 * @since 2021-03-19
 */
@Service
public class CourseCollectServiceImpl extends ServiceImpl<CourseCollectMapper, CourseCollect> implements ICourseCollectService {

}
