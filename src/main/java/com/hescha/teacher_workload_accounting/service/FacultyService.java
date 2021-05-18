package com.hescha.teacher_workload_accounting.service;


import com.hescha.teacher_workload_accounting.utils.ColumnIndexes;
import com.hescha.teacher_workload_accounting.entity.Faculty;
import com.hescha.teacher_workload_accounting.repository.FacultyRepository;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FacultyService extends CrudImpl<Faculty> {

    public FacultyRepository repository;

    @Autowired
    public FacultyService(FacultyRepository repository) {
        super(repository);
        this.repository = repository;
    }
    public Faculty createFacultyIfNotExists(HSSFRow rowWithInfo) {
        Faculty faculty =
                new Faculty(rowWithInfo.getCell(ColumnIndexes.COLUMN_NUMBER_TO_FACULTY).toString());
        Faculty facultyByName =
                repository.findByNameIgnoreCase(faculty.getName());
        if (facultyByName == null) {
            create(faculty);
            return repository.findByNameIgnoreCase(faculty.getName());
        }
        return facultyByName;
    }
}