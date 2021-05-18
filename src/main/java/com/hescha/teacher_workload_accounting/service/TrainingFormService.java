package com.hescha.teacher_workload_accounting.service;


import com.hescha.teacher_workload_accounting.utils.ColumnIndexes;
import com.hescha.teacher_workload_accounting.entity.TrainingForm;
import com.hescha.teacher_workload_accounting.repository.TrainingFormRepository;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TrainingFormService extends CrudImpl<TrainingForm> {

    public TrainingFormRepository repository;

    @Autowired
    public TrainingFormService(TrainingFormRepository repository) {
        super(repository);
        this.repository = repository;
    }
    public TrainingForm createTrainingFormIfNotExists(HSSFRow rowWithInfo) {
        TrainingForm trainingForm =
                new TrainingForm(rowWithInfo.getCell(ColumnIndexes.COLUMN_NUMBER_TO_TRAINING_FORM).toString());
        TrainingForm trainingFormByName =
                repository.findByNameIgnoreCase(trainingForm.getName());
        if (trainingFormByName == null) {
            create(trainingForm);
            return repository.findByNameIgnoreCase(trainingForm.getName());
        }
        return trainingFormByName;
    }
}