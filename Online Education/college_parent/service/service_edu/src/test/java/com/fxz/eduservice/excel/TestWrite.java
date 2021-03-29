package com.fxz.eduservice.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestWrite {
    public static void main(String[] args) {
        String fileName = "D:/wirte.xlsx";
        EasyExcel.write(fileName,demoData.class).sheet("学生信息").doWrite(getData());
    }

    private static List<demoData> getData() {
        List<demoData> list = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            demoData data  = new demoData();
            data.setSno(i);
            data.setSname("冯熹章"+i);
            list.add(data);
        }
        return list;
    }
}
