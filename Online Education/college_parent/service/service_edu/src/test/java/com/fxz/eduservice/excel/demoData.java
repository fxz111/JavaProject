package com.fxz.eduservice.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class demoData {
    @ExcelProperty("学生编号")
    private Integer sno;
    @ExcelProperty("学生信息")
    private String sname;
}
