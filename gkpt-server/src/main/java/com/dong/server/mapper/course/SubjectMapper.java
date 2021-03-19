package com.dong.server.mapper.course;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dong.server.pojo.course.Subject;
import com.dong.server.pojo.course.vm.SubjectVM;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author caojingdong
 * @since 2021-03-18
 */
@Repository
public interface SubjectMapper extends BaseMapper<Subject> {

    List<SubjectVM> selectSubjectListByParentId(Long parentId);
}
