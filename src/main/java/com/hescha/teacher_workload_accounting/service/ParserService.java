package com.hescha.teacher_workload_accounting.service;

import com.hescha.teacher_workload_accounting.entity.*;
import com.hescha.teacher_workload_accounting.service.*;
import com.hescha.teacher_workload_accounting.test.ExcelReader;
import com.hescha.teacher_workload_accounting.utils.ColumnIndexes;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Iterator;

@Service
public class ParserService {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DisciplineService disciplineService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private SpecialityService specialityService;

    @Autowired
    private TableRowService tableRowService;

    @Autowired
    private TableRowDateService tableRowDateService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TrainingFormService trainingFormService;

/*
    @GetMapping("/")
    public String get(Model model) {
        String filePath = "src\\main\\resources\\Для нагрузки.xls";
        parseFileByPath(filePath);
        return "index";
    }
*/
    public void parseFileByPath(String filePath) {
        System.out.println("start parsing file: "+filePath);
        HSSFWorkbook wb = ExcelReader.readWorkbook(filePath);
        if (wb != null) {
            System.out.println("file opened ");
            HSSFSheet sheet = wb.getSheetAt(0);
            Iterator rowIter = sheet.rowIterator();
            while (rowIter.hasNext()) {
                HSSFRow row = (HSSFRow) rowIter.next();
                if (isHeaderRow(row)) {
                    continue;
                }
                if (isRowEnded(row)) {
                    break;
                }
                saveParseDataToDatabase(row, (HSSFRow) rowIter.next());
            }
        } else{
            System.out.println("workbook is null");
        }
        System.out.println("end parsing.");
    }

    private boolean isRowEnded(HSSFRow row) {
        return row.getCell(ColumnIndexes.COLUMN_NUMBER_TO_SPECIALITY) != null
                && (row.getCell(ColumnIndexes.COLUMN_NUMBER_TO_TOTAL).toString().equals("Итого")
                || row.getCell(ColumnIndexes.COLUMN_NUMBER_TO_GROUP_NAME).toString().equals(""));
    }

    private boolean isHeaderRow(HSSFRow row) {
        return row.getCell(ColumnIndexes.COLUMN_NUMBER_TO_YEAR) != null
                && row.getCell(ColumnIndexes.COLUMN_NUMBER_TO_YEAR).toString().equals(
                "Год");
    }

    private void saveParseDataToDatabase(HSSFRow rowWithGroupName,
                                         HSSFRow rowWithInfo) {
        TableRowDate tableRowDate =
                tableRowDateService.createTableRowDateIfNotExists(rowWithInfo);
        Teacher teacher = teacherService.createTeacherIfNotExists(rowWithInfo);
        Department department =
                departmentService.createDepartmentIfNotExists(rowWithInfo);
        Faculty faculty = facultyService.createFacultyIfNotExists(rowWithInfo);
        TrainingForm trainingForm =
                trainingFormService.createTrainingFormIfNotExists(rowWithInfo);
        Speciality speciality =
                specialityService.createSpecialityIfNotExists(rowWithInfo);
        Group group = groupService.createGroupIfNotExists(rowWithGroupName,
                rowWithInfo, department, trainingForm, faculty, speciality);
        Discipline discipline =
                disciplineService.createDisciplineIfNotExists(rowWithInfo);
        tableRowService.createTableRowIfNotExists(rowWithInfo, tableRowDate,
                teacher, group, discipline);
    }
}
