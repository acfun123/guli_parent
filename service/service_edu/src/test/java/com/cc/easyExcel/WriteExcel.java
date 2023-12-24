package com.cc.easyExcel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class WriteExcel {

    /**
     * 最简单的读
     * <p>1. 创建excel对应的实体对象 参照{@link DemoData}
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link DemoDataListener}
     * <p>3. 直接读即可
     */
    @Test
    public void simpleRead() {
        String filePath = "/Users/cc/Desktop/1.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(filePath, DemoData.class, new DemoDataListener()).sheet("模板").doRead();
    }

    @Test
    public void testWrite(){
        String filePath = "/Users/cc/Desktop/1.xlsx";
        EasyExcel.write(filePath,DemoData.class).sheet("模板").doWrite(data());
    }

    private List<DemoData> data() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 35; i++) {
            DemoData data = new DemoData();
            data.setCode("code"+i);
            data.setName("name"+i);
            list.add(data);
        }
        return list;
    }

    @Test
    public void test2() {
        
    }
}
