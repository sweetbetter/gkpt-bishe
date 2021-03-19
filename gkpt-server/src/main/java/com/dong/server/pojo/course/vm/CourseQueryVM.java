package com.dong.server.pojo.course.vm;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: CourseQueryVM
 * @Author: caojingdong
 * @Description: 课程搜索条件封装对象
 * @Date: 2021/3/19 20:16
 */
@Data
public class CourseQueryVM implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private Long teacherId;
    private Long subjectParentId;
    private Long subjectId;
}
