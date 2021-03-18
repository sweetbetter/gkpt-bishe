package com.dong.server.service.course;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dong.server.controller.course.vm.TeacherQueryVM;
import com.dong.server.pojo.course.Teacher;


/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author caojingdong
 * @since 2021-03-09
 */
public interface ITeacherService extends IService<Teacher> {

    IPage<Teacher> selectTeacherWithPage(TeacherQueryVM teacherQueryVM, Page<Teacher> pageParam);
    Boolean removeAvatar(String id);
}
