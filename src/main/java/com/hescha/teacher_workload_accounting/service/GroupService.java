package com.hescha.teacher_workload_accounting.service;


import com.hescha.teacher_workload_accounting.utils.ColumnIndexes;
import com.hescha.teacher_workload_accounting.entity.*;
import com.hescha.teacher_workload_accounting.repository.GroupRepository;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GroupService extends CrudImpl<Group> {

    public GroupRepository repository;

    @Autowired
    public GroupService(GroupRepository repository) {
        super(repository);
        this.repository = repository;
    }
    public Group createGroupIfNotExists(HSSFRow rowWithGroupName,
                                         HSSFRow rowWithInfo,
                                         Department department,
                                         TrainingForm trainingForm,
                                         Faculty faculty,
                                         Speciality speciality) {
        Group group =
                new Group(rowWithGroupName.getCell(ColumnIndexes.COLUMN_NUMBER_TO_GROUP_NAME).toString(),
                        trainingForm);
        Group groupByName =
                repository.findByNameIgnoreCaseAndTrainingForm(group.getName(), group.getTrainingForm());
        if (groupByName == null) {
            String course =
                    rowWithInfo.getCell(ColumnIndexes.COLUMN_NUMBER_TO_COURSE).toString();
            String studentCount =
                    rowWithInfo.getCell(ColumnIndexes.COLUMN_NUMBER_TO_STUDENT_COUNT).toString();
            String groupCount =
                    rowWithInfo.getCell(ColumnIndexes.COLUMN_NUMBER_TO_GROUP_COUNT).toString();
            group = new Group(group.getName(), department, faculty,
                    trainingForm, speciality, course, studentCount, groupCount);
            create(group);
            return repository.findByNameIgnoreCaseAndTrainingForm(group.getName(), group.getTrainingForm());
        }
        return groupByName;
    }
}