package com.hescha.teacher_workload_accounting.service;


import com.hescha.teacher_workload_accounting.utils.ColumnIndexes;
import com.hescha.teacher_workload_accounting.entity.Department;
import com.hescha.teacher_workload_accounting.repository.DepartmentRepository;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DepartmentService extends CrudImpl<Department> {

    public DepartmentRepository repository;

    @Autowired
    public DepartmentService(DepartmentRepository repository) {
        super(repository);
        this.repository = repository;
    }
    public Department createDepartmentIfNotExists(HSSFRow rowWithInfo) {
        Department department =
                new Department(rowWithInfo.getCell(ColumnIndexes.COLUMN_NUMBER_TO_DEPARTMENT).toString());
        Department departmentByName =
                repository.findByNameIgnoreCase(department.getName());
        if (departmentByName == null) {
            create(department);
            return repository.findByNameIgnoreCase(department.getName());
        }
        return departmentByName;
    }
}