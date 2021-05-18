package com.hescha.teacher_workload_accounting.test;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.Iterator;

public class Starter {
    public static void main(String[] args) {
        String filename = "src\\main\\resources\\Для нагрузки.xls";
        HSSFWorkbook wb = ExcelReader.readWorkbook(filename);
        if (wb != null) {
            HSSFSheet sheet = wb.getSheetAt(0);

            Iterator rowIter = sheet.rowIterator();
            while (rowIter.hasNext()) {
                HSSFRow row = (HSSFRow) rowIter.next();

                Iterator cellIter = row.cellIterator();
                while (cellIter.hasNext()) {
                    HSSFCell cell = (HSSFCell) cellIter.next();

                    System.out.print(cell + "\t");
                }
                System.out.println();

            }

        } else {
            System.out.println("null");
        }
    }
}
