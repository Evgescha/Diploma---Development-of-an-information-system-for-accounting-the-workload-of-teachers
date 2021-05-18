package com.hescha.teacher_workload_accounting.repository;

import com.hescha.teacher_workload_accounting.entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Long> {
    Discipline findByNameIgnoreCase(String name);
}