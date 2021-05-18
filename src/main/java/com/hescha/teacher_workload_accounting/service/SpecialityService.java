package com.hescha.teacher_workload_accounting.service;


import com.hescha.teacher_workload_accounting.utils.ColumnIndexes;
import com.hescha.teacher_workload_accounting.entity.Speciality;
import com.hescha.teacher_workload_accounting.repository.SpecialityRepository;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SpecialityService extends CrudImpl<Speciality> {

    public SpecialityRepository repository;

    @Autowired
    public SpecialityService(SpecialityRepository repository) {
        super(repository);
        this.repository = repository;
    }
    public Speciality createSpecialityIfNotExists(HSSFRow rowWithInfo) {
        Speciality speciality =
                new Speciality(rowWithInfo.getCell(ColumnIndexes.COLUMN_NUMBER_TO_SPECIALITY).toString());
        Speciality specialityByName =
                repository.findByNameIgnoreCase(speciality.getName());
        if (specialityByName == null) {
            create(speciality);
            return repository.findByNameIgnoreCase(speciality.getName());
        }
        return specialityByName;
    }
}