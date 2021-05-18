package com.hescha.teacher_workload_accounting.repository;

import com.hescha.teacher_workload_accounting.entity.TableRowDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRowDateRepository extends JpaRepository<TableRowDate,
        Long> {
    TableRowDate findByYearAndSemester(String year, String semester);
}