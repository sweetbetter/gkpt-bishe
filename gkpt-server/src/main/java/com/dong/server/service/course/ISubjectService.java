package com.dong.server.service.course;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dong.server.pojo.course.Subject;

import java.io.InputStream;


/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author caojingdong
 * @since 2021-03-18
 */
public interface ISubjectService extends IService<Subject> {
    //传入前端上传的excel文件流
    void subjectImport(InputStream inputStream);
}
