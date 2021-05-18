package com.hescha.teacher_workload_accounting.service;


import com.hescha.teacher_workload_accounting.utils.ColumnIndexes;
import com.hescha.teacher_workload_accounting.entity.Teacher;
import com.hescha.teacher_workload_accounting.repository.TeacherRepository;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TeacherService extends CrudImpl<Teacher> {

    public TeacherRepository repository;

    @Autowired
    public TeacherService(TeacherRepository repository) {
        super(repository);
        this.repository = repository;
    }
    public Teacher createTeacherIfNotExists(HSSFRow rowWithInfo) {
        Teacher teacher =
                new Teacher(rowWithInfo.getCell(ColumnIndexes.COLUMN_NUMBER_TO_TEACHER).toString());
        Teacher teacherByName =
                repository.findByNameIgnoreCase(teacher.getName());
        if (teacherByName == null) {
            create(teacher);
            return repository.findByNameIgnoreCase(teacher.getName());
        }
        return teacherByName;
    }
}