package com.dong.server.pojo.course.vm;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: TeacherQueryVM
 * @Author: caojingdong
 * @Description: 老师查询对象
 * @Date: 2021/3/9 21:35
 */
@Data
public class TeacherQueryVM  implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private Integer level;
    private String joinDateBegin;
    private String joinDateEnd;
}
