package com.hescha.teacher_workload_accounting.repository;

import com.hescha.teacher_workload_accounting.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByNameIgnoreCase(String name);
}