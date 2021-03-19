package com.dong.server.service.course.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.server.mapper.course.CourseDescriptionMapper;
import com.dong.server.mapper.course.CourseMapper;
import com.dong.server.pojo.course.Course;
import com.dong.server.pojo.course.CourseDescription;
import com.dong.server.pojo.course.vm.CourseInfoForm;
import com.dong.server.service.course.ICourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private CourseDescriptionMapper courseDescriptionMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long saveCourseInfo(CourseInfoForm courseInfoForm) {
        //保存两个信息
        Course course=new Course();
        BeanUtils.copyProperties(courseInfoForm,course);
        course.setStatus(Course.COURSE_UNPUBLISHED);
        baseMapper.insert(course);//自动回填id

        CourseDescription description=new CourseDescription();
        description.setDescription(courseInfoForm.getDescription());
        description.setId(course.getId());
        courseDescriptionMapper.insert(description);
        return course.getId();
    }
}
