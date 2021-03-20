package com.dong.server.pojo.course.vm;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: CoursePulishFormVM
 * @Author: caojingdong
 * @Description: 课程发布页面对应对象
 * @Date: 2021/3/20 23:19
 */
@Data
public class CoursePulishFormVM implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectParentTitle;
    private String subjectChildrenTitle;
    private String teacherName;
    private String price;//只用于显示
}
