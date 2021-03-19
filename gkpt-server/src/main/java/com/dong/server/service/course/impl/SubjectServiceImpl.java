package com.dong.server.service.course.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.dong.server.listener.ExcelSubjectDataListener;
import com.dong.server.mapper.course.SubjectMapper;
import com.dong.server.pojo.course.Subject;
import com.dong.server.pojo.excel.SubJectData;
import com.dong.server.pojo.course.vm.SubjectVM;
import com.dong.server.service.course.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

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

    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public void subjectImport(InputStream inputStream) {
        EasyExcel.read(inputStream, SubJectData.class, new ExcelSubjectDataListener(subjectMapper))
                .excelType(ExcelTypeEnum.XLSX)
                .sheet().doRead();
    }

    @Override
    public List<SubjectVM> subjectList() {
        return baseMapper.selectSubjectListByParentId(0L);
    }
}
