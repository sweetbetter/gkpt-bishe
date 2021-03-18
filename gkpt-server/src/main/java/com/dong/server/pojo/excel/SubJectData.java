package com.dong.server.pojo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @ClassName: SubJectData
 * @Author: caojingdong
 * @Description: 课程分类excel对象
 * @Date: 2021/3/18 21:22
 */
@Data
public class SubJectData {
    @ExcelProperty(value = "一级分类")
    private String levelOne;

    @ExcelProperty(value = "二级分类")
    private String levelTwo;

}
