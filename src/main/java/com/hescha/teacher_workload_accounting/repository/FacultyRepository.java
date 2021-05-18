package com.hescha.teacher_workload_accounting.repository;

import com.hescha.teacher_workload_accounting.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Faculty findByNameIgnoreCase(String name);
}