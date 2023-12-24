package com.cc.eduservice.config;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.read.listener.ReadListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cc.eduservice.entity.EduSubject;
import com.cc.eduservice.service.EduSubjectService;
import com.ccbase.exception.GuliException;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class EduSubjectListener implements ReadListener<EduSubject> {

    private EduSubjectService eduSubjectService;

    public EduSubjectListener(){}
    public EduSubjectListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    @Override
    public void onException(Exception e, AnalysisContext analysisContext) throws Exception {
        log.error("ReadExcelOnException {0}", e);
    }

    @Override
    public void invokeHead(Map<Integer, CellData> map, AnalysisContext analysisContext) {
        log.info("invokeHead {}", map);
    }

    @Override
    public void invoke(EduSubject data, AnalysisContext context) {
        if(data == null){
            throw new GuliException(20001, "数据行为空");
        }
        log.info("data {}", data);
        processSubject(data);
    }

    private void processSubject(EduSubject data) {
        //判断一级科目是否存在
        String oneSubject = data.getParentTitle();
        String twoSubject = data.getTitle();
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", oneSubject);
        queryWrapper.eq("parent_id", "0");
        EduSubject res = eduSubjectService.getOne(queryWrapper);
        //一级科目不存在
        if(res == null){
            res = new EduSubject();
            res.setTitle(oneSubject);
            res.setSort(0);
            res.setParentId("0");
            eduSubjectService.save(res);
            processTwoSubject(twoSubject, res);
        }else {
            //一级科目存在，查询二级科目是否存在
            log.warn("OneSubject already exists {}", res);
            processTwoSubject(twoSubject, res);
        }
    }

    private void processTwoSubject(String twoSubject, EduSubject oneSubject) {
        QueryWrapper<EduSubject> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("title", twoSubject);
        queryWrapper2.eq("parent_id", oneSubject.getId());
        EduSubject res2 = eduSubjectService.getOne(queryWrapper2);
        if(res2 == null){
            EduSubject eduSubject = new EduSubject();
            eduSubject.setTitle(twoSubject);
            eduSubject.setSort(0);
            eduSubject.setParentId(oneSubject.getId());
            eduSubjectService.save(eduSubject);
        }else {
            log.warn("TwoSubject already exists {}", res2);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("doAfterAllAnalysed finished!");
    }

    @Override
    public boolean hasNext(AnalysisContext analysisContext) {
        return true;
    }

}
