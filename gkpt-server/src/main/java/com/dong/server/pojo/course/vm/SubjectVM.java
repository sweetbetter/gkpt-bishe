package com.dong.server.pojo.course.vm;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: SubjectVM
 * @Author: caojingdong
 * @Description: 封装subject一级和二级菜单数据对象
 * @Date: 2021/3/19 14:01
 */
@Data
public class SubjectVM implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private Integer sort;
    private List<SubjectVM> children = new ArrayList<>();
}
