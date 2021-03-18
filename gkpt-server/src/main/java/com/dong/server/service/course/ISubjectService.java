package com.dong.server.service.course;

import com.baomidou.mybatisplus.extension.service.IService;


import javax.security.auth.Subject;
import java.io.InputStream;

/**
 * @ClassName: ISubjectService
 * @Author: caojingdong
 * @Description: 课程分类数据层接口
 * @Date: 2021/3/18 21:29
 */
public interface ISubjecrvice extends IService<Subject> {
    //接受前端传来的excel输入留,批量导入
    void subjectImport(InputStream inputStream);
}
