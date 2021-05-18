package com.hescha.teacher_workload_accounting.repository;

import com.hescha.teacher_workload_accounting.entity.Group;
import com.hescha.teacher_workload_accounting.entity.TrainingForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByNameIgnoreCase(String name);

    Group findByNameIgnoreCaseAndTrainingForm(String name,
                                              TrainingForm trainingForm);
}