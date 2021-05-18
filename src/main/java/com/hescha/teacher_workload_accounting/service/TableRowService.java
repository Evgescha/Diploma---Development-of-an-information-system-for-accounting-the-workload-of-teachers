package com.hescha.teacher_workload_accounting.service;


import com.hescha.teacher_workload_accounting.utils.ColumnIndexes;
import com.hescha.teacher_workload_accounting.entity.*;
import com.hescha.teacher_workload_accounting.repository.TableRowRepository;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TableRowService extends CrudImpl<TableRow> {

    public TableRowRepository repository;

    @Autowired
    public TableRowService(TableRowRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public void createTableRowIfNotExists(HSSFRow rowWithInfo,
                                          TableRowDate tableRowDate,
                                          Teacher teacher, Group group,
                                          Discipline discipline) {

        TableRow tableRow = readTableRowFromHSSRow(rowWithInfo, tableRowDate,
                teacher, group, discipline);

        TableRow tableRowByFind =
                repository.findByTableRowDateAndTeacherAndDisciplineAndGroup(tableRowDate, teacher, discipline, group);
        if (tableRowByFind == null) {
            create(tableRow);
        } else {
            tableRowByFind.update(tableRow.getThreadCount(),
                    tableRow.getLectureCount(), tableRow.getPracticalCount(),
                    tableRow.getLaboratoryCount(),
                    tableRow.getConsultationCount(), tableRow.getExam(),
                    tableRow.getWatching(), tableRow.getDifferencialZachet(),
                    tableRow.getZachet(), tableRow.getRgr(),
                    tableRow.getCourseWork(), tableRow.getCourseProject(),
                    tableRow.getControlWork(), tableRow.getIndividualWork(),
                    tableRow.getOtherWork());
        }
    }

    private TableRow readTableRowFromHSSRow(HSSFRow rowWithInfo,
                                            TableRowDate tableRowDate,
                                            Teacher teacher, Group group,
                                            Discipline discipline) {
        String threadNumber =
                rowWithInfo.getCell(ColumnIndexes.COLUMN_NUMBER_TO_THREAD_NUMBER).toString();
        String threadCount =
                rowWithInfo.getCell(ColumnIndexes.COLUMN_NUMBER_TO_THREAD_COUNT).toString();
        int lectureCount =
                Integer.parseInt(getStringValueFromCellOrEmpty(rowWithInfo.getCell(ColumnIndexes.COLUMN_NUMBER_TO_LECTURE_COUNT)).replace(".0", ""));
        int practicalCount =
                Integer.parseInt(getStringValueFromCellOrEmpty(rowWithInfo.getCell(ColumnIndexes.COLUMN_NUMBER_TO_PRACTICAL_COUNT)).replace(".0", ""));
        int laboratoryCount =
                Integer.parseInt(getStringValueFromCellOrEmpty(rowWithInfo.getCell(ColumnIndexes.COLUMN_NUMBER_TO_LABORATORY_COUNT)).replace(".0", ""));
        float consultationCount =
                Float.parseFloat(getStringValueFromCellOrEmpty(rowWithInfo.getCell(ColumnIndexes.COLUMN_NUMBER_TO_CONSULTATION_COUNT)));
        float exam =
                Float.parseFloat(getStringValueFromCellOrEmpty(rowWithInfo.getCell(ColumnIndexes.COLUMN_NUMBER_TO_EXAM)));
        float watching =
                Float.parseFloat(getStringValueFromCellOrEmpty(rowWithInfo.getCell(ColumnIndexes.COLUMN_NUMBER_TO_WATCHING)));
        float differencialZachet =
                Float.parseFloat(getStringValueFromCellOrEmpty(rowWithInfo.getCell(ColumnIndexes.COLUMN_NUMBER_TO_DIFFERENCIAL_ZACHET)));
        float zachet =
                Float.parseFloat(getStringValueFromCellOrEmpty(rowWithInfo.getCell(ColumnIndexes.COLUMN_NUMBER_TO_ZACHET)));
        float rgr =
                Float.parseFloat(getStringValueFromCellOrEmpty(rowWithInfo.getCell(ColumnIndexes.COLUMN_NUMBER_TO_RGR)));
        float courseWork =
                Float.parseFloat(getStringValueFromCellOrEmpty(rowWithInfo.getCell(ColumnIndexes.COLUMN_NUMBER_TO_COURSE_WORK)));
        float courseProject =
                Float.parseFloat(getStringValueFromCellOrEmpty(rowWithInfo.getCell(ColumnIndexes.COLUMN_NUMBER_TO_COURSE_PROJECT)));
        float controlWork =
                Float.parseFloat(getStringValueFromCellOrEmpty(rowWithInfo.getCell(ColumnIndexes.COLUMN_NUMBER_TO_CONTROL_WORK)));
        float individualWork =
                Float.parseFloat(getStringValueFromCellOrEmpty(rowWithInfo.getCell(ColumnIndexes.COLUMN_NUMBER_TO_INDIVIDUALW_ORK)));
        float otherWork =
                Float.parseFloat(getStringValueFromCellOrEmpty(rowWithInfo.getCell(ColumnIndexes.COLUMN_NUMBER_TO_OTHER_WORK)));
        return new TableRow(tableRowDate, teacher, discipline,
                threadNumber, group, threadCount, lectureCount,
                practicalCount, laboratoryCount, consultationCount, exam,
                watching, differencialZachet, zachet, rgr, courseWork,
                courseProject, controlWork, individualWork, otherWork);
    }

    private String getStringValueFromCellOrEmpty(HSSFCell cell) {
        String s = cell.toString();
        if (s == null || s.isEmpty()) return "0";
        return s.replace(",", ".").replace("*", "");
    }

}