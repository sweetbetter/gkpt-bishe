package com.dong.server.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.dong.server.pojo.excel.SubJectData;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: ExcelSubjectDataListener
 * @Author: caojingdong
 * @Description: 课程分类的监听器 easyExcel
 * @Date: 2021/3/18 21:25
 */
@Slf4j
public class ExcelSubjectDataListener extends AnalysisEventListener<SubJectData> {


    //每一行解析时都会调用
    @Override
    public void invoke(SubJectData subJectData, AnalysisContext analysisContext) {
            log.info("解析一条记录");
            //解析一级&二级标题
        String levelOne=subJectData.getLevelOne();
        String levelTwo=subJectData.getLevelTwo();
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("全部数据解析完成");
    }
}
