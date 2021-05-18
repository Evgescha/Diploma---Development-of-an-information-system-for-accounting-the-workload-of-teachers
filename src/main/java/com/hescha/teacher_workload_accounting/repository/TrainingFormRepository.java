package com.hescha.teacher_workload_accounting.repository;

import com.hescha.teacher_workload_accounting.entity.TrainingForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingFormRepository extends JpaRepository<TrainingForm, Long> {
    TrainingForm findByNameIgnoreCase(String name);
}