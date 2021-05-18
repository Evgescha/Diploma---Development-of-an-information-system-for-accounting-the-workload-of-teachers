package com.hescha.teacher_workload_accounting.service;


import com.hescha.teacher_workload_accounting.utils.ColumnIndexes;
import com.hescha.teacher_workload_accounting.entity.Discipline;
import com.hescha.teacher_workload_accounting.repository.DisciplineRepository;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DisciplineService extends CrudImpl<Discipline> {

    public DisciplineRepository repository;

    @Autowired
    public DisciplineService(DisciplineRepository repository) {
        super(repository);
        this.repository = repository;
    }
    public Discipline createDisciplineIfNotExists(HSSFRow rowWithInfo) {
        Discipline discipline =
                new Discipline(rowWithInfo.getCell(ColumnIndexes.COLUMN_NUMBER_TO_DISCIPLINE).toString());
        Discipline disciplineByName =
                repository.findByNameIgnoreCase(discipline.getName());
        if (disciplineByName == null) {
            create(discipline);
            return repository.findByNameIgnoreCase(discipline.getName());
        }
        return disciplineByName;
    }
}