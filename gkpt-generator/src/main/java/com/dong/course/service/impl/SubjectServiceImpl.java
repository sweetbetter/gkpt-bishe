package com.dong.course.service.impl;

import com.dong.course.pojo.Subject;
import com.dong.course.mapper.SubjectMapper;
import com.dong.course.service.ISubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author caojingdong
 * @since 2021-03-18
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements ISubjectService {

}
