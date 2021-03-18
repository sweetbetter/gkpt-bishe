package com.dong.server.service.course.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.dong.server.controller.course.vm.TeacherQueryVM;
import com.dong.server.mapper.course.TeacherMapper;
import com.dong.server.pojo.course.Teacher;
import com.dong.server.service.IFileService;
import com.dong.server.service.course.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author caojingdong
 * @since 2021-03-09
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

    @Autowired
    IFileService fileService;

    @Override
    public IPage<Teacher> selectTeacherWithPage(TeacherQueryVM teacherQueryVM, Page<Teacher> pageParam) {
        //分页显示查询
        //1.排序
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        //2.查询
         if(teacherQueryVM ==null)
         {
             return baseMapper.selectPage(pageParam,queryWrapper);
         }
        String name = teacherQueryVM.getName();
        Integer level = teacherQueryVM.getLevel();
        String begin = teacherQueryVM.getJoinDateBegin();
        String end = teacherQueryVM.getJoinDateEnd();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.likeRight("name", name);
        }

        if (level != null) {
            queryWrapper.eq("level", level);
        }

        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("join_date", begin);
        }

        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("join_date", end);
        }


        return baseMapper.selectPage(pageParam,queryWrapper);
    }

    //通过id删除讲师头像
    @Override
    public Boolean removeAvatar(String id) {
        Teacher teacher=baseMapper.selectById(id);
        if(teacher!=null){
            String avatarUrl=teacher.getAvatar();
            if(!StringUtils.isEmpty(avatarUrl)){
                fileService.removeFile(avatarUrl);
            }
        }
        return true;
    }
}
