package com.hescha.teacher_workload_accounting.service;


import com.hescha.teacher_workload_accounting.utils.ColumnIndexes;
import com.hescha.teacher_workload_accounting.entity.TableRowDate;
import com.hescha.teacher_workload_accounting.repository.TableRowDateRepository;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TableRowDateService extends CrudImpl<TableRowDate> {

    public TableRowDateRepository repository;

    @Autowired
    public TableRowDateService(TableRowDateRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public TableRowDate createTableRowDateIfNotExists(HSSFRow rowWithInfo) {
        TableRowDate tableRowDate =
                new TableRowDate(rowWithInfo.getCell(ColumnIndexes.COLUMN_NUMBER_TO_YEAR).toString(),
                        rowWithInfo.getCell(ColumnIndexes.COLUMN_NUMBER_TO_SEMESTER).toString());

        TableRowDate byYearAndSemester =
                repository.findByYearAndSemester(tableRowDate.getYear(), tableRowDate.getSemester());
        if (byYearAndSemester == null) {
            create(tableRowDate);
            return repository.findByYearAndSemester(tableRowDate.getYear(), tableRowDate.getSemester());
        }
        return byYearAndSemester;
    }

}