package com.cc.easyExcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class DemoDataListener implements ReadListener<DemoData> {
    /**
     * 单次缓存的数据量
     */
    public static final int BATCH_COUNT = 100;
    /**
     *临时存储
     */
    private List<DemoData> cachedDataList = new ArrayList<>(BATCH_COUNT);

    @Override
    public void onException(Exception e, AnalysisContext analysisContext) throws Exception {

    }

    @Override
    public void invokeHead(Map<Integer, CellData> map, AnalysisContext analysisContext) {
        log.info("invokeHead {}", map);
    }

    @Override
    public void invoke(DemoData data, AnalysisContext context) {
        log.info("data {}", data);
        cachedDataList.add(data);
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            cachedDataList = new ArrayList<>(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
    }

    @Override
    public boolean hasNext(AnalysisContext analysisContext) {
        return true;
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
        log.info("存储数据库成功！");
    }
}
