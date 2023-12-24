package com.cc.easyExcel;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DemoData {
    @ExcelProperty("学生编号")
    private String code;
    @ExcelProperty("学生姓名")
    private String name;
}
