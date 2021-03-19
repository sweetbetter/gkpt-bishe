package com.dong.server.service.course.impl;


import cn.hutool.core.bean.BeanUtil;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.server.mapper.course.CourseDescriptionMapper;
import com.dong.server.mapper.course.CourseMapper;
import com.dong.server.pojo.course.Course;
import com.dong.server.pojo.course.CourseDescription;
import com.dong.server.pojo.course.vm.CourseFormVM;
import com.dong.server.pojo.course.vm.CourseInfoForm;
import com.dong.server.pojo.course.vm.CourseQueryVM;
import com.dong.server.service.course.ICourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

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
        Course course=new Course();
        course.setStatus(Course.COURSE_UNPUBLISHED);
        //spring的BeanUtils有问题....无法复制...浪费爸爸时间
        BeanUtil.copyProperties(courseInfoForm,course);
        baseMapper.insert(course);//自动回填id

        CourseDescription description=new CourseDescription();
        description.setDescription(courseInfoForm.getDescription());
        description.setId(course.getId());
        courseDescriptionMapper.insert(description);
        return course.getId();
    }

    @Override
    public CourseInfoForm getCourseById(String id) {
        Course course=baseMapper.selectById(id);
        if(course==null) return null;
        CourseDescription courseDescription=courseDescriptionMapper.selectById(id);
        CourseInfoForm courseInfoForm=new CourseInfoForm();
        BeanUtil.copyProperties(course,courseInfoForm);
        courseInfoForm.setDescription(courseDescription.getDescription());
        return courseInfoForm;
    }

    @Override
    public void updateCourseInfoById(CourseInfoForm courseInfoForm) {
        Course course=new Course();
        BeanUtil.copyProperties(courseInfoForm,course);
        baseMapper.updateById(course);
        CourseDescription courseDescription=new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());

        courseDescription.setId(courseInfoForm.getId());
        courseDescriptionMapper.updateById(courseDescription);
    }

    @Override
    public IPage<CourseFormVM> selectPage(Long page, Long limit, CourseQueryVM courseQueryVM) {
        QueryWrapper<Course> courseQueryWrapper=new QueryWrapper<>();
        courseQueryWrapper.orderByDesc("c.gmt_create");
        String title=courseQueryVM.getTitle();
        Long teacherId=courseQueryVM.getTeacherId();
        Long subjectParentId=courseQueryVM.getSubjectParentId();
        Long subjectChildren=courseQueryVM.getSubjectId();
        if(!StringUtils.isEmpty(title)) {
            courseQueryWrapper.like("c.title",title);
        }
//      需要debug看看如果subjectParentId等为空 封装的是什么
        if(teacherId!=null) {
            courseQueryWrapper.eq("c.teacherId",teacherId);
        }
        if(subjectParentId!=null) {
            courseQueryWrapper.eq("c.subject_parent_id",subjectParentId);
        }
        if(subjectChildren!=null) {
            courseQueryWrapper.eq("c.subject_id",subjectChildren);
        }

        Page<CourseFormVM> courseFormVMPage = new Page<>(page, limit);

        List<CourseFormVM> result=baseMapper.selectPageByQueryVM();

        return null;
    }
}
