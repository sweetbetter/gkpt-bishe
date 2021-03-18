package com.dong.server.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dong.server.mapper.course.SubjectMapper;
import com.dong.server.pojo.course.Subject;
import com.dong.server.pojo.excel.SubJectData;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: ExcelSubjectDataListener
 * @Author: caojingdong
 * @Description: 课程分类的监听器 easyExcel
 * @Date: 2021/3/18 21:25
 */
@Slf4j
//@AllArgsConstructor
//@NoArgsConstructor
public class ExcelSubjectDataListener extends AnalysisEventListener<SubJectData> {
    //ExcelSubjectDataListener 不是直接放入spirng管理的
//    @Autowired  所以不可注入
    private SubjectMapper subjectMapper;

    public ExcelSubjectDataListener() {
    }

    //通过new时传入mapper对象
    public ExcelSubjectDataListener(SubjectMapper subjectMapper) {
        this.subjectMapper = subjectMapper;
    }

    //每一行解析时都会调用
    @Override
    public void invoke(SubJectData subJectData, AnalysisContext analysisContext) {
        log.info("解析一条记录");
        //解析一级&二级标题
        String levelOne = subJectData.getLevelOne();
        String levelTwo = subJectData.getLevelTwo();
        //封装数据
            //查看该一级标题是否存在，判断是否要插入
        Subject subjectLevelOne=this.getByTitle(levelOne);
        Long parentId=null;
        if(subjectLevelOne==null){
            Subject subject=new Subject();
            subject.setParentId(0L);
            subject.setTitle(levelOne);
            //存入数据库
            subjectMapper.insert(subject);
            parentId=subject.getId();
        }else {
            parentId=subjectLevelOne.getId();
        }

        Subject subjectLevelTwo=this.getLevelTwo(levelTwo,parentId);
        if(subjectLevelTwo==null){
            //封装二级类别
            Subject subject=new Subject();
            subject.setTitle(levelTwo);
            subject.setParentId(parentId);
            subjectMapper.insert(subject);
        }
    }

    private Subject getByTitle(String title){
        QueryWrapper<Subject> subjectQueryWrapper = new QueryWrapper<>();
        subjectQueryWrapper.eq("title",title);
        subjectQueryWrapper.eq("parent_id","0");
        return subjectMapper.selectOne(subjectQueryWrapper);
    }

//判断相同二级分类是否存在
    private Subject getLevelTwo(String levelTwo,long parentId){
        QueryWrapper<Subject> subjectQueryWrapper = new QueryWrapper<>();
        subjectQueryWrapper.eq("title",levelTwo);
        subjectQueryWrapper.eq("parent_id",parentId);
        return subjectMapper.selectOne(subjectQueryWrapper);
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("全部数据解析完成");
    }
}
