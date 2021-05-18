package com.hescha.teacher_workload_accounting.repository;

import com.hescha.teacher_workload_accounting.entity.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, Long> {
    Speciality findByNameIgnoreCase(String name);
}