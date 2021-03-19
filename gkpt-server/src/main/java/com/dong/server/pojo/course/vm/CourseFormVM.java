package com.dong.server.pojo.course.vm;

import java.io.Serializable;

/**
 * @ClassName: CourseFormVM
 * @Author: caojingdong
 * @Description: 多表联查后封装对象(即传输到前端的对象)
 * @Date: 2021/3/19 20:17
 */
public class CourseFormVM implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String title;
    private String subjectParentTitle;
    private String subjectChildrenTitle;
    private String teacherName;
    private Integer lessonNum;
    private String price;
    private String cover;
    private Long buyCount;
    private Long viewCount;
    private String status;
    private String gmtCreate;
}
