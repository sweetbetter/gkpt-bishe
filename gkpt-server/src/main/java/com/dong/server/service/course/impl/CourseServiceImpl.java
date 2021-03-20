package com.dong.server.service.course.impl;


import cn.hutool.core.bean.BeanUtil;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.server.mapper.course.*;
import com.dong.server.pojo.course.*;
import com.dong.server.pojo.course.vm.CourseFormVM;
import com.dong.server.pojo.course.vm.CourseInfoForm;
import com.dong.server.pojo.course.vm.CoursePulishFormVM;
import com.dong.server.pojo.course.vm.CourseQueryVM;
import com.dong.server.service.IFileService;
import com.dong.server.service.course.ICourseService;

import com.dong.server.util.R;
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
    @Autowired
    private IFileService fileService;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CourseCollectMapper courseCollectMapper;
    @Autowired
    private ChapterMapper chapterMapper;
    @Autowired
    private VideoMapper videoMapper;

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
    public CourseInfoForm getCourseById(Long id) {
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

        List<CourseFormVM> result=baseMapper.selectPageByQueryVM(courseFormVMPage,courseQueryWrapper);
        //记得要设置 mybatispls回去查询所有行
        return courseFormVMPage.setRecords(result);
    }

    //删除封面图片
    @Override
    public boolean removeCoverById(Long id) {
        Course course=baseMapper.selectById(id);
        if(course != null){
            String cover=course.getCover();
            if(!StringUtils.hasText(cover)){
               fileService.removeFile(cover);
            }
        }
        return true;
    }

    //ps：分布式、高并发项目一般不设立外键依赖  外键和级联(更新风险)影响插入熟读
    // 所以要在业务层解决，要先删除子表数据 再删父表数据
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeCourseById(Long id) {
        //删除VIDEO
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",id);
        videoMapper.delete(videoQueryWrapper);

        //删除章节
        QueryWrapper<Chapter>  chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",id);
        chapterMapper.delete(chapterQueryWrapper);

        //删除评论
        QueryWrapper<Comment>  commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("course_id",id);
        commentMapper.delete(commentQueryWrapper);

        //删除收藏
        QueryWrapper<CourseCollect>  collectQueryWrapper= new QueryWrapper<>();
        collectQueryWrapper.eq("course_id",id);
        courseCollectMapper.delete(collectQueryWrapper);

        //删除详情
        courseDescriptionMapper.deleteById(id);

        //删除Course
//       int i= baseMapper.deleteById(id);

        return    this.removeById(id);
    }

    @Override
    public CoursePulishFormVM getCoursePulishVMById(Long id) {
        return baseMapper.getCoursePulishVMById(id);
    }

    @Override
    public boolean publishCourseById(Long id) {
        Course course=new Course();
        course.setId(id);
        course.setStatus(Course.COURSE_PUBLISHED);
        return this.updateById(course);
    }
}
